package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.User;

public class GuestMapper {

    public static GuestBasicDTO toGuestBasicDTO(Guest guest) {
        if (guest == null) return null;

        GuestBasicDTO dto = new GuestBasicDTO();
        dto.setId(guest.getId());
        dto.setFirstName(guest.getFirstName());
        dto.setLastName(guest.getLastName());
        dto.setUsername(guest.getUsername());
        dto.setPassword(guest.getPassword());
        dto.setEmail(guest.getEmail());
        dto.setPhoneNumber(guest.getPhoneNumber());
        dto.setAddress(guest.getAddress());
        dto.setActive(guest.isActive());
        dto.setRole(guest.getRole());

        return dto;
    }

    public static Guest toGuestEntity(GuestBasicDTO dto) {
        if (dto == null) return null;

        Guest guest = new Guest();
        guest.setId(dto.getId());
        guest.setFirstName(dto.getFirstName());
        guest.setLastName(dto.getLastName());
        guest.setUsername(dto.getUsername());
        guest.setPassword(dto.getPassword());
        guest.setEmail(dto.getEmail());
        guest.setPhoneNumber(dto.getPhoneNumber());
        guest.setAddress(dto.getAddress());
        guest.setActive(dto.isActive());
        guest.setRole(dto.getRole());

        return guest;
    }
}
