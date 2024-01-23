package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findAllAgentsByAgencyId(Long agencyId);

}
