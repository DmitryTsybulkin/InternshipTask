package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.User;

import java.util.List;

public class UserDTO extends User {

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public String getPatronymic() {
        return super.getPatronymic();
    }

    @Override
    public List<Municipality> getMunicipality() {
        return super.getMunicipality();
    }

    @Override
    public boolean isAdmin() {
        return super.isAdmin();
    }

    @Override
    public boolean isEditor() {
        return super.isEditor();
    }
}
