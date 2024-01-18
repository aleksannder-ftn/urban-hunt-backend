package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;

import java.util.Optional;

public interface AgentService {

    Agent saveAgent(Agent agent);

    Optional<Agent> findAgentById(Long id);

    void deleteAgent(Agent agent);
}
