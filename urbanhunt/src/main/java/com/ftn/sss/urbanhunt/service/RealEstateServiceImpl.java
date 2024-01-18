package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.repository.interfaces.RealEstateRepository;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealEstateServiceImpl implements RealEstateService {

    RealEstateRepository realEstateRepository;

    @Autowired
    public RealEstateServiceImpl(RealEstateRepository realEstateRepository) {
        this.realEstateRepository = realEstateRepository;
    }

    @Override
    public Optional<RealEstate> findById(Long id) {
        return realEstateRepository.findById(id);
    }

    @Override
    public Optional<RealEstate> save(RealEstate realEstate) {
        return Optional.of(realEstateRepository.save(realEstate));
    }

    @Override
    public RealEstate createRealEstate(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @Override
    public void delete(RealEstate realEstate) {
        realEstateRepository.delete(realEstate);
    }
}
