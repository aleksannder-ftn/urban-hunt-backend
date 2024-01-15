package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.agent.AgentBasicDTO;
import com.ftn.sss.urbanhunt.model.Agent;

public class AgentMapper {

    public static AgentBasicDTO toAgentBasicDTO(Agent agent) {
        if (agent == null) return null;

        AgentBasicDTO dto = new AgentBasicDTO();
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

        return dto;
    }

    public static Agent toAgentEntity(AgentBasicDTO dto) {
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

        return agent;
    }
}