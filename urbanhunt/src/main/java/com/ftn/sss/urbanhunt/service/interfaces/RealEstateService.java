package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;

import java.util.List;
import java.util.Optional;

public interface RealEstateService {
    List<RealEstate> findAll();
    List<RealEstate> find(User user, String location, Float surfaceFrom, Float surfaceTo,
                          Float priceFrom, Float priceTo, RealEstateType realEstateType, TransactionType transactionType);
    Optional<RealEstate> findById(Long id);
    RealEstate findRealEstateById(Long id);
    Optional<RealEstate> save(RealEstate realEstate);
    void delete(RealEstate realEstate);
    RealEstate addRealEstateWithImages(RealEstateBasicDTO requestDto, UserService userService, AgencyService agencyService, ImageService imageService);
    RealEstate editRealEstate(RealEstateBasicDTO requestDTO, AgencyService agencyService, ImageService imageService);
    int activateRealEstate(Long id);
    int deactivateRealEstate(Long id);
}
