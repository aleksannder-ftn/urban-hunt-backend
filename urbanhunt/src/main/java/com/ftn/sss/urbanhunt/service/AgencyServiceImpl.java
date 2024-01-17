package com.ftn.sss.urbanhunt.service;


import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.repository.interfaces.AgencyRepository;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public Agency createAgency(Agency agency) {
        try {
            return agencyRepository.createAgency(agency);
        } catch ( Exception e) {
            return null;
        }

    }

    @Override
    public Agency findAgencyByOwnerId(Long ownerId) {
        return agencyRepository.findAgencyByOwnerId(ownerId);
    }
}
