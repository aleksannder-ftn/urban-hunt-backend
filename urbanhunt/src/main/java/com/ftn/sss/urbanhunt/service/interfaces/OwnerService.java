package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getAllOwners();

    Owner getOwnerById(Long id);

    int deactivateOwner(Owner owner);

    int activateOwner(Owner owner);

}
