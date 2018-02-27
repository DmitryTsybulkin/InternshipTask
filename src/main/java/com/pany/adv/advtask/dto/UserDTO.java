package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Municipality;

import java.util.List;

public class UserDTO {

    public long id;
    public String login;
    public String name;
    public String surname;
    public String patronymic;
    public String role;
    public List<Municipality> municipalities;
    public boolean admin;
    public boolean editor;

}
