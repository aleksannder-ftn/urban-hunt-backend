package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.dto.mapper.RealEstateMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.repository.interfaces.RealEstateRepository;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.ImageService;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<RealEstate> findAllByAgentId(Long id) {
        return realEstateRepository.findAllByAgentId(id);
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
    }}
