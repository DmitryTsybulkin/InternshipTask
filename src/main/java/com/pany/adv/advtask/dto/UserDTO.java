package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;
import com.sun.javafx.scene.layout.region.Margins;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Convert;
import javax.persistence.Converter;
import java.util.List;

public class UserDTO {
    
    public long id;
    public String login;
    public String name;
    public String surname;
    public String patronymic;
    public List<Municipality> municipalities;
    public Roles role;

}
