package com.pany.adv.advtask.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;  // will hashcode-password

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "municipalities", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "municipality_id"))
    private List<Municipality> municipality; // n:m

    @Column(name = "is_admin")
    private boolean admin;

    @Column(name = "is_editor")
    private boolean editor;

    public User() {}

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isEditor() {
        return editor;
    }

    public void setEditor(boolean editor) {
        this.editor = editor;
    }

    public List<Municipality> getMunicipality() {
        return municipality;
    }

    public void setMunicipality(List<Municipality> municipality) {
        this.municipality = municipality;
    }

    public String toString() {
        return "* User Entry(" + "id: " + id + "login: " + login + "password: " +
                password + "name: " + name + "surname: " +
                surname + "patronymic: " + patronymic + "municipality: " + ").";
    }

}