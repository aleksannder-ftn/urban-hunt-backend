package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;

import java.util.List;

public interface AgentService {
    List<Agent> getAllAgents();

    Agent getAgentById(Long id);

    int deactivateAgent(Agent agent);
}
