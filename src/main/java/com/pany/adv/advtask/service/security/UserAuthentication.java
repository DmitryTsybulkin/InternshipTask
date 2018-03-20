package com.pany.adv.advtask.service.security;

import com.pany.adv.advtask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserAuthentication implements Authentication {

    private final UserDetailsImpl user;
    private boolean authenticated = true;

    public UserAuthentication(User user) {
        this.user = UserDetailsImplBuilder.anUserModel()
                .withUsername(user.getLogin())
                .withPassword(user.getPassword())
                .withRoles(Collections.singletonList(user.getRole()))
                .withIsAccountNonExpired(true)
                .withIsAccountNonLocked(true)
                .withIsCredentialsNonExpired(true)
                .withIsEnabled(true).build();
    }

    public UserAuthentication(String id, UserDetailsImpl user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
