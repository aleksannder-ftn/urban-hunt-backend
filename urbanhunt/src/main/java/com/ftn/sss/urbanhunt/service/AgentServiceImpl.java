package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.repository.interfaces.AgentRepository;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final AgencyService agencyService;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, AgencyService agencyService) {
        this.agentRepository = agentRepository;
        this.agencyService = agencyService;
    }

    @Override
    public Agent saveAgent(Agent agent) {
        agent.setActive(true);
        return agentRepository.save(agent);
    }

    @Override
    public Object findAgentByRealEstate(Long id) {
        return agentRepository.findAgentByRealEstate(id);
    }

    @Override
    public Optional<Agent> findAgentById(Long id) {
        return agentRepository.findById(id);
    }

    @Override
    public void deleteAgent(Agent agent) {
        agentRepository.delete(agent);
    }

    @Override
    public List<Agent> findAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public List<Agent> findAllAgentsByAgencyId(Long agencyId) {
        return agentRepository.findAllAgentsByAgencyId(agencyId);
    }

    @Override
    public List<Agent> findMostPopularAgentsByAgencyId(Long agencyId) {
        Agency ag = agencyService.findAgencyById(agencyId);
        return agentRepository.findMostPopularAgentsByAgencyId(ag);
    }
}
