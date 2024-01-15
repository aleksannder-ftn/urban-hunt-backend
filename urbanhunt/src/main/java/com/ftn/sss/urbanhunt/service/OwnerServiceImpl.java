package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.repository.interfaces.OwnerRepository;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> getAllOwners() {
        try {
            return ownerRepository.getAllOwners();
        } catch (Exception e) {
            throw null;
        }
    }
}
