package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;

import java.util.List;

public interface AgentRepository {
    List<Agent> getAllAgents();

    Agent getAgentById(Long id);

    int deactivateAgent(Agent agent);

}
