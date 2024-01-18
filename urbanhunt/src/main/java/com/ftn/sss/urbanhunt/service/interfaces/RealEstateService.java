package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.RealEstate;

import java.util.Optional;

public interface RealEstateService {
    Optional<RealEstate> findById(Long id);
    Optional<RealEstate> save(RealEstate realEstate);
    RealEstate createRealEstate(RealEstate realEstate);
    void delete(RealEstate realEstate);
}
