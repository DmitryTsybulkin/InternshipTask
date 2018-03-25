package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Roles;

import java.util.List;

public class UserDTO {
    
    public long id;
    public String login;
    public String name;
    public String surname;
    public String patronymic;
    public List<MunicipalityDTO> municipalities;
    public Roles role;

}
