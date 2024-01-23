package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.agent.AgentBasicDTO;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;

public class AgentMapper {

    public static AgentBasicDTO toAgentBasicDTO(Agent agent) {
        if (agent == null) return null;

        AgentBasicDTO dto = new AgentBasicDTO();
        return getAgentBasicDTO(agent, dto);
    }

    static AgentBasicDTO getAgentBasicDTO(Agent agent, AgentBasicDTO dto) {
        dto.setId(agent.getId());
        dto.setFirstName(agent.getFirstName());
        dto.setLastName(agent.getLastName());
        dto.setUsername(agent.getUsername());
        dto.setPassword(agent.getPassword());
        dto.setEmail(agent.getEmail());
        dto.setPhoneNumber(agent.getPhoneNumber());
        dto.setAddress(agent.getAddress());
        dto.setActive(agent.isActive());
        dto.setRole(agent.getRole());
        dto.setAgencyId(agent.getAgency().getId());
        return dto;
    }

    public static Agent toAgentEntity(AgentBasicDTO dto, AgencyService agencyService) {
        if (dto == null) return null;

        Agent agent = new Agent();

        agent.setId(dto.getId());
        agent.setFirstName(dto.getFirstName());
        agent.setLastName(dto.getLastName());
        agent.setUsername(dto.getUsername());
        agent.setPassword(dto.getPassword());
        agent.setEmail(dto.getEmail());
        agent.setPhoneNumber(dto.getPhoneNumber());
        agent.setAddress(dto.getAddress());
        agent.setActive(dto.isActive());
        agent.setRole(dto.getRole());
        agent.setAgency(agencyService.findAgencyById(dto.getAgencyId()));

        return agent;
    }
}
