package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateDetailedDTO;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.ImageService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;

import java.util.List;

public class RealEstateMapper {

    public static Object RealEstateBasicDTO(RealEstate realEstate) {
        if (realEstate == null) return null;
        RealEstateBasicDTO dto = new RealEstateBasicDTO();
        return getRealEstateBasicDTO(realEstate, dto);
    }

    public static RealEstateBasicDTO getRealEstateBasicDTO(RealEstate realEstate, RealEstateBasicDTO realEstateBasicDTO) {
        realEstateBasicDTO.setId(realEstate.getId());
        realEstateBasicDTO.setRealEstateType(realEstate.getRealEstateType());
        realEstateBasicDTO.setActive(realEstate.isActive());
        realEstateBasicDTO.setLocation(realEstate.getLocation());
        realEstateBasicDTO.setPrice(realEstate.getPrice());
        realEstateBasicDTO.setRating(realEstate.getRating());
        realEstateBasicDTO.setViewCount(realEstate.getViewCount());
        realEstateBasicDTO.setTransactionType(realEstate.getTransactionType());
        realEstateBasicDTO.setSurfaceArea(realEstate.getSurfaceArea());
        realEstateBasicDTO.setAgentId(realEstate.getAgent().getId());
        realEstateBasicDTO.setAgencyId(realEstate.getAgency().getId());
        List<Image> images = realEstate.getImage();
        List<String> imagePaths = images.stream().map(Image:: getImagePath).toList();
        realEstateBasicDTO.setImages(imagePaths);
        return realEstateBasicDTO;
    }

    public static List<RealEstateBasicDTO> toRealEstateListDTO(List<RealEstate> realEstates, List<RealEstateBasicDTO> dto) {
        for(RealEstate r : realEstates) {
            RealEstateBasicDTO basic = new RealEstateBasicDTO();
            dto.add(RealEstateMapper.getRealEstateBasicDTO(r, basic));
        }

        return dto;
    }

    public static RealEstate toRealEstateEntity(RealEstateBasicDTO realEstateBasicDTO, UserService userService, AgencyService agencyService, ImageService imageService) {
        if (realEstateBasicDTO == null) return null;

        RealEstate realEstate = new RealEstate();
        return getRealEstateEntity(realEstateBasicDTO, realEstate, userService, agencyService, imageService);
    }

    static RealEstate getRealEstateEntity(RealEstateBasicDTO realEstateBasicDTO, RealEstate realEstate, UserService userService, AgencyService agencyService,
                                          ImageService imageService) {
        realEstate.setId(realEstateBasicDTO.getId());
        realEstate.setRealEstateType(realEstateBasicDTO.getRealEstateType());
        realEstate.setActive(realEstateBasicDTO.isActive());
        realEstate.setLocation(realEstateBasicDTO.getLocation());
        realEstate.setPrice(realEstateBasicDTO.getPrice());
        realEstate.setRating(realEstateBasicDTO.getRating());
        realEstate.setViewCount(realEstateBasicDTO.getViewCount());
        realEstate.setTransactionType(realEstateBasicDTO.getTransactionType());
        realEstate.setSurfaceArea(realEstateBasicDTO.getSurfaceArea());
        realEstate.setAgent((Agent) userService.findUserById(realEstateBasicDTO.getAgentId()));
        realEstate.setAgency(agencyService.findAgencyById(realEstateBasicDTO.getAgencyId()));
        realEstate.setImage(imageService.findAllByRealEstateId(realEstateBasicDTO.getId()));

        return realEstate;
    }

    // ONLY TRANSFER FOR POPULARITY.
    public static Object RealEstateDetailedDTO(RealEstate realEstate) {
        if (realEstate == null) return null;
        RealEstateDetailedDTO dto = new RealEstateDetailedDTO();
        return getRealEstateDetailedDTO(realEstate, dto);
    }

    public static RealEstateDetailedDTO getRealEstateDetailedDTO(RealEstate realEstate, RealEstateDetailedDTO realEstateDetailedDTO) {
        realEstateDetailedDTO.setId(realEstate.getId());
        realEstateDetailedDTO.setRealEstateType(realEstate.getRealEstateType());
        realEstateDetailedDTO.setActive(realEstate.isActive());
        realEstateDetailedDTO.setLocation(realEstate.getLocation());
        realEstateDetailedDTO.setPrice(realEstate.getPrice());
        realEstateDetailedDTO.setRating(realEstate.getRating());
        realEstateDetailedDTO.setViewCount(realEstate.getViewCount());
        realEstateDetailedDTO.setTransactionType(realEstate.getTransactionType());
        realEstateDetailedDTO.setSurfaceArea(realEstate.getSurfaceArea());
        realEstateDetailedDTO.setAgentId(realEstate.getAgent().getId());
        realEstateDetailedDTO.setAgencyId(realEstate.getAgency().getId());
        List<Image> images = realEstate.getImage();
        List<String> imagePaths = images.stream().map(Image:: getImagePath).toList();
        realEstateDetailedDTO.setImages(imagePaths);
        realEstateDetailedDTO.setNumberOfActiveTours(null);
        return realEstateDetailedDTO;
    }

    public static List<RealEstateDetailedDTO> toRealEstateDetailedListDTO(List<RealEstate> realEstates, List<RealEstateDetailedDTO> dto) {
        for(RealEstate r : realEstates) {
            RealEstateDetailedDTO basic = new RealEstateDetailedDTO();
            dto.add(RealEstateMapper.getRealEstateDetailedDTO(r, basic));
        }

        return dto;
    }
}
