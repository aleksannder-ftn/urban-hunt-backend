package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency save(Agency agency);
    Agency findAgencyByOwnerId(Long ownerId);
    @Query("SELECT a FROM Agency a JOIN a.agents ag WHERE ag.id = :agentId")
    Agency findAgencyByAgentId(Long agentId);
    Agency findAgencyById(Long id);
    Optional<Agency> findById(Long id);
}
