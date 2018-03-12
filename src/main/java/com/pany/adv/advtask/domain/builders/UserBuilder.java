package com.pany.adv.advtask.domain.builders;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;

import java.util.List;

public final class UserBuilder {
    private String login;
    private String password;  // will hashcode-password
    private String name;
    private String surname;
    private String patronymic;
    private List<Municipality> municipality; // n:m
    private Roles role;

    public UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder withPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public UserBuilder withMunicipality(List<Municipality> municipality) {
        this.municipality = municipality;
        return this;
    }

    public UserBuilder withRole(Roles role) {
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setMunicipalities(municipality);
        user.setRole(role);
        return user;
    }
}
