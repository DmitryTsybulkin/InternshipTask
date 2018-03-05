package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.User;
import com.sun.javafx.scene.layout.region.Margins;

import javax.persistence.Convert;
import javax.persistence.Converter;
import java.util.List;

public class UserDTO {

    private long id;
    private String login;
    private String name;
    private String surname;
    private String patronymic;
    private List<Municipality> municipalities;
    private boolean admin;
    private boolean editor;

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public void setMunicipalities(List<Municipality> municipalities) {
        this.municipalities = municipalities;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setEditor(boolean editor) {
        this.editor = editor;
    }

    public String getLogin() {
        return login;
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

    public long getId() {
        return id;
    }

    public List<Municipality> getMunicipalities() {
        return municipalities;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isEditor() {
        return editor;
    }
}
