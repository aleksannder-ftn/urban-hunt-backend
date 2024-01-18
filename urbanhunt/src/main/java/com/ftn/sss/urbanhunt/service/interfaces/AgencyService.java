package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Agency;

public interface AgencyService {
    Agency findAgencyById(Long id);
    Agency createAgency(Agency agency);
    Agency findAgencyByOwnerId(Long ownerId);
}
