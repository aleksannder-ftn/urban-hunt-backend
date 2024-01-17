package com.ftn.sss.urbanhunt.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    GUEST, ADMINISTRATOR, AGENT, OWNER;

    @Override
    public String getAuthority() {
        return name();
    }

}
