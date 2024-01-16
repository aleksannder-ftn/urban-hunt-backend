package com.ftn.sss.urbanhunt.dto.user;

import com.ftn.sss.urbanhunt.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {

    private String accessToken;

    private Long expiresIn;

    private Role role;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.role = null;
    }

    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public UserTokenState(String accessToken, long expiresIn, Role role) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
    }
}
