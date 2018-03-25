package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.exceptions.*;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.config.SecurityEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    private final
    UserRep userRep;

    @Autowired
    public UserService(UserRep userRep) {
        this.userRep = userRep;
    }

    //---------------------------------------CRUD----------------------------------------

    public void createUser(User user) {
        if (user == null) {
            throw new MissingParametersException();
        }
        if (userRep.countUsersByLogin(user.getLogin()) > 0) {
            throw new DuplicateEntityException();
        }
        userRep.save(user);
    }

    public User findById(long id) {
        return userRep.findUserById(id).orElseThrow(ResourceNotFound::new);
    }

    public List<User> findAll() {
        List<User> users = userRep.findAll();
        if (users.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return users;
    }

    @Transactional
    public void updateUser(long id, User user) {
        if (user == null) {
            throw new MissingParametersException();
        }
        if (userRep.countUsersByLogin(user.getLogin()) > 0) {
            throw new DuplicateLoginException();
        }
        User targetUser = findById(id);
        targetUser.setLogin(user.getLogin());
        targetUser.setMunicipalities(user.getMunicipalities());
        targetUser.setName(user.getName());
        targetUser.setPatronymic(user.getPatronymic());
        targetUser.setSurname(user.getSurname());
        targetUser.setRole(user.getRole());
    }

    public void deleteUser(long id) {
        User user = userRep.findUserById(id).orElseThrow(ResourceNotFound::new);
        userRep.delete(user);
    }
}
