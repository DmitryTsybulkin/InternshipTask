package com.pany.adv.advtask.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {

    USER,
    EDITOR,
    ADMIN;

    private static final String PREFIX = "ROLE_";

    @Override
    public String getAuthority() {
        return PREFIX + this.name();
    }

}
