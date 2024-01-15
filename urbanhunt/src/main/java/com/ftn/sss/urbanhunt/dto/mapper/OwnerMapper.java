package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.owner.OwnerBasicDTO;
import com.ftn.sss.urbanhunt.model.Owner;

public class OwnerMapper {

    public static OwnerBasicDTO toOwnerBasicDTO(Owner owner) {
        if (owner == null) return null;

        OwnerBasicDTO dto = new OwnerBasicDTO();
        dto.setId(owner.getId());
        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setUsername(owner.getUsername());
        dto.setPassword(owner.getPassword());
        dto.setEmail(owner.getEmail());
        dto.setPhoneNumber(owner.getPhoneNumber());
        dto.setAddress(owner.getAddress());
        dto.setActive(owner.isActive());
        dto.setRole(owner.getRole());

        return dto;
    }

    public static Owner toOwnerEntity(OwnerBasicDTO dto) {
        if (dto == null) return null;

        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setUsername(dto.getUsername());
        owner.setPassword(dto.getPassword());
        owner.setEmail(dto.getEmail());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setAddress(dto.getAddress());
        owner.setActive(dto.isActive());
        owner.setRole(dto.getRole());

        return owner;
    }
}