package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.repository.interfaces.AgentRepository;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Optional<Agent> findAgentById(Long id) {
        return agentRepository.findById(id);
    }

    @Override
    public void deleteAgent(Agent agent) {
        agentRepository.delete(agent);
    }
}
