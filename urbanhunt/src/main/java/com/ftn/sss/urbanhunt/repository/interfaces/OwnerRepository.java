package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Owner;

import java.util.List;

public interface OwnerRepository {
    List<Owner> getAllOwners();

    Owner getOwnerById(Long id);

    int deactivateOwner(Owner owner);
}
