package com.pany.adv.advtask.service.security;

import com.pany.adv.advtask.domain.Roles;

import java.util.List;

public final class UserDetailsImplBuilder {
    private List<Roles> roles;
    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private UserDetailsImplBuilder() {
    }

    public static UserDetailsImplBuilder anUserModel() {
        return new UserDetailsImplBuilder();
    }

    public UserDetailsImplBuilder withRoles(List<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public UserDetailsImplBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsImplBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDetailsImplBuilder withIsAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
        return this;
    }

    public UserDetailsImplBuilder withIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
        return this;
    }

    public UserDetailsImplBuilder withIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        return this;
    }

    public UserDetailsImplBuilder withIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public UserDetailsImpl build() {
        return new UserDetailsImpl(roles, password, username, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }
}
