package com.pany.adv.advtask.service.security;

import com.pany.adv.advtask.domain.Roles;

import java.util.List;

public final class UserModelBuilder {
    private List<Roles> roles;
    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private UserModelBuilder() {
    }

    public static UserModelBuilder anUserModel() {
        return new UserModelBuilder();
    }

    public UserModelBuilder withRoles(List<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public UserModelBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserModelBuilder withIsAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
        return this;
    }

    public UserModelBuilder withIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
        return this;
    }

    public UserModelBuilder withIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        return this;
    }

    public UserModelBuilder withIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public UserModel build() {
        return new UserModel(roles, password, username, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }
}
