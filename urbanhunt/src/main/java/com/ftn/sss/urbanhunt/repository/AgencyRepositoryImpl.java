package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.repository.interfaces.AgencyRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class AgencyRepositoryImpl implements AgencyRepository {

    private EntityManager entityManager;
    @Override
    public Agency createAgency(Agency agency) {
        entityManager.persist(agency);
        return agency;
    }

    @Override
    public Agency findAgencyByOwnerId(Long ownerId) {
        try {
            return entityManager.find(Agency.class, ownerId);
        } catch (Exception e) {
            return null;
        }
    }
}
