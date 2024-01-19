package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;

import java.util.List;
import java.util.Optional;

public interface RealEstateService {
    List<RealEstate> findAll();
    List<RealEstate> findAllByAgentId(Long id);
    Optional<RealEstate> findById(Long id);
    RealEstate findRealEstateById(Long id);
    Optional<RealEstate> save(RealEstate realEstate);
    void delete(RealEstate realEstate);
    RealEstate addRealEstateWithImages(RealEstateBasicDTO requestDto, UserService userService, AgencyService agencyService, ImageService imageService);
}
