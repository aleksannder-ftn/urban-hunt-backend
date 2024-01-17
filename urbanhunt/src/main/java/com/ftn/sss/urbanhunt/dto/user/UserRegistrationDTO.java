package com.ftn.sss.urbanhunt.dto.user;

import com.ftn.sss.urbanhunt.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int phoneNumber;
    private String address;
    private boolean active;
    private Role role;
}
