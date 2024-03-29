package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.dto.mapper.RealEstateMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateDetailedDTO;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
import com.ftn.sss.urbanhunt.repository.interfaces.RealEstateRepository;
import com.ftn.sss.urbanhunt.repository.interfaces.RealEstateSpecification;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.ImageService;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RealEstateServiceImpl implements RealEstateService {

    RealEstateRepository realEstateRepository;

    @Autowired
    public RealEstateServiceImpl(RealEstateRepository realEstateRepository) {
        this.realEstateRepository = realEstateRepository;
    }

    @Override
    public List<RealEstate> findAll() {
        return realEstateRepository.findAll();
    }

    @Override
    public List<RealEstate> findByActiveTrue() {
        return realEstateRepository.findByActiveTrue();
    }

    @Override
    public List<RealEstate> find(User user, String location,
                                 Float surfaceFrom, Float surfaceTo,
                                 Float priceFrom, Float priceTo, RealEstateType realEstateType,
                                 TransactionType transactionType) {

        Specification<RealEstate> spec = RealEstateSpecification.withOptionalFields((Agent) user, location, surfaceFrom, surfaceTo, priceFrom, priceTo, realEstateType, transactionType);
        return realEstateRepository.findAll(spec);
    }

    @Override
    public Optional<RealEstate> findById(Long id) {
        return realEstateRepository.findById(id);
    }

    @Override
    public RealEstate findRealEstateById(Long id) {
        return realEstateRepository.findRealEstateById(id);
    }

    @Override
    public Optional<RealEstate> save(RealEstate realEstate) {
        return Optional.of(realEstateRepository.save(realEstate));
    }

    @Override
    public void delete(RealEstate realEstate) {
        realEstateRepository.delete(realEstate);
    }

    @Override
    @Transactional
    public RealEstate addRealEstateWithImages(RealEstateBasicDTO requestDto, UserService userService, AgencyService agencyService, ImageService imageService) {
        RealEstate realEstate = RealEstateMapper.toRealEstateEntity(requestDto, userService, agencyService, imageService);
        realEstate.setActive(true);
        realEstate.setAgency(agencyService.findAgencyById(requestDto.getAgencyId()));
        realEstate.setAgent((Agent) userService.findUserById(requestDto.getAgentId()));
        realEstate = realEstateRepository.save(realEstate);

        if (requestDto.getImages() != null) {
            for (String imagePath : requestDto.getImages()) {
                Image image = new Image();
                image.setImagePath(imagePath);
                image.setRealEstate(realEstate);
                imageService.save(image);
            }
        }
        return realEstate;
    }

    @Override
    public RealEstate editRealEstate(RealEstateBasicDTO requestDTO, AgencyService agencyService, ImageService imageService) {
        RealEstate realEstateForEdit = realEstateRepository.findRealEstateById(requestDTO.getId());
        realEstateForEdit.setSurfaceArea(requestDTO.getSurfaceArea());
        realEstateForEdit.setTransactionType(requestDTO.getTransactionType());
        realEstateForEdit.setPrice(requestDTO.getPrice());
        realEstateForEdit.setRealEstateType(requestDTO.getRealEstateType());
        realEstateForEdit.setLocation(requestDTO.getLocation());
/*
        imageService.deleteAllByRealEstate(realEstateForEdit);

        if (requestDTO.getImages() != null) {
            for (String imagePath : requestDTO.getImages()) {
                Image image = new Image();
                image.setImagePath(imagePath);
                image.setRealEstate(realEstateForEdit);
                imageService.save(image);
            }
        } */

        return realEstateRepository.save(realEstateForEdit);
    }

    @Override
    public int activateRealEstate(Long id) {
        RealEstate realEstate = realEstateRepository.findRealEstateById(id);
        if (realEstate == null) {
            return 0;
        } else if (realEstate.isActive()) {
            return 0;
        }
        realEstate.setActive(true);
        realEstateRepository.save(realEstate);
        return 1;
    }

    @Override
    public int deactivateRealEstate(Long id) {
        RealEstate realEstate = realEstateRepository.findRealEstateById(id);
        if (realEstate == null) {
            return 0;
        } else if (!realEstate.isActive()) {
            return 0;
        }
        realEstate.setActive(false);
        realEstateRepository.save(realEstate);
        return 1;
    }

    @Override
    public List<RealEstateDetailedDTO> findAllByPopularity(Long agencyId) {
        List<Object[]> resultList = realEstateRepository.findAllByPopularity(agencyId);
        List<RealEstateDetailedDTO> resultDTOList = new ArrayList<>();

        for (Object[] result : resultList) {
            RealEstateDetailedDTO dto = new RealEstateDetailedDTO();
            dto.setId((Long) result[0]);
            dto.setLocation((String) result[1]);
            dto.setPrice((Float) result[2]);
            dto.setSurfaceArea((Float) result[3]);
            String transactionTypeString = (String) result[4];
            TransactionType transactionType = TransactionType.valueOf(transactionTypeString);
            dto.setTransactionType(transactionType);
            String realEstateTypeString = (String) result[5];
            RealEstateType realEstateType = RealEstateType.valueOf(realEstateTypeString);
            dto.setRealEstateType(realEstateType);
            dto.setNumberOfActiveTours((Long) result[6]);

            resultDTOList.add(dto);
        }

        return resultDTOList;
    }

    @Override
    public void rentRealEstate(Long realEstateId) {
        realEstateRepository.rentRealEstate(realEstateId);
    }

    @Override
    public void buyRealEstate(Long realEstateId) {
        realEstateRepository.buyRealEstate(realEstateId);
    }

    @Override
    public Float sumPricesForSoldRealEstates() {
        Float soldPrices = realEstateRepository.sumPricesOfSoldRealEstates();
        if(soldPrices != null) {
            soldPrices /= 1000;
        }
        return soldPrices;
    }

    @Override
    public Float sumPricesForRentedRealEstates() {
        Float rentedPrices = realEstateRepository.sumPricesOfRentedRealEstates();
        if(rentedPrices != null) {
            rentedPrices /= 100;
        }
        return rentedPrices;
    }

    @Override
    public Agent findAgentById(Long id) {
        return realEstateRepository.findAgentById(id);
    }
}