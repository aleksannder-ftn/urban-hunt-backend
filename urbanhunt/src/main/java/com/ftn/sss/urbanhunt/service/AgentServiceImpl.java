package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.repository.interfaces.AgentRepository;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agent> getAllAgents() {
        try {
            return agentRepository.getAllAgents();
        } catch (Exception e) {
            throw null;
        }
    }

    @Override
    public Agent getAgentById(Long id) {
        try {
            return agentRepository.getAgentById(id);
        } catch (Exception e) {
            throw null;
        }
    }

    @Override
    public int deactivateAgent(Agent agent) {
        try {
            return agentRepository.deactivateAgent(agent);
        } catch (Exception e) {
            throw null;
        }
    }

    @Override
    public int activateAgent(Agent agent) {
        try {
            return agentRepository.activateAgent(agent);
        } catch (Exception e) {
            throw null;
        }
    }
}
