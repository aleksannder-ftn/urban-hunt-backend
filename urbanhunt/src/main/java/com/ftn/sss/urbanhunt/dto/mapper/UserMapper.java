package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.*;

public class UserMapper {

    public static UserBasicDTO toUserBasicDTO(User user) {
        if (user == null) return null;

        UserBasicDTO dto = new UserBasicDTO();
        return getUserBasicDTO(user, dto);
    }

    static UserBasicDTO getUserBasicDTO(User user, UserBasicDTO dto) {
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        dto.setActive(user.isActive());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toUserEntity(UserBasicDTO dto) {
        if (dto == null) return null;

        User user;

        user = switch (dto.getRole()) {
            case GUEST -> new Guest();
            case ADMINISTRATOR -> new Administrator();
            case AGENT -> new Agent();
            case OWNER -> new Owner();
            default -> throw new IllegalStateException("Unexpected value: " + dto.getRole());
        };

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setActive(dto.isActive());
        user.setRole(dto.getRole());

        return user;
    }
}
