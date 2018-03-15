package com.pany.adv.advtask.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {

    USER,
    EDITOR,
    ADMIN;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
