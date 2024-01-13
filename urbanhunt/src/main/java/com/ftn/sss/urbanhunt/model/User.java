package com.ftn.sss.urbanhunt.model;

import com.ftn.sss.urbanhunt.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int phoneNumber;
    private String address;
    private Role role;

}
