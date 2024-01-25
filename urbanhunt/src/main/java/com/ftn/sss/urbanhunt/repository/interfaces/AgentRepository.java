package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findAllAgentsByAgencyId(Long id);
    @Query("SELECT a FROM Agent a WHERE a.agency = :agency ORDER BY a.averageRating DESC")
    List<Agent> findMostPopularAgentsByAgencyId(Agency agency);
}
