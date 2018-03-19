package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.exceptions.*;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.security.SecurityEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final
    UserRep userRep;

    private final
    MunicipalityRep municipalityRep;

    private final SecurityEncoder securityEncoder;

    @Autowired
    public UserService(UserRep userRep, MunicipalityRep municipalityRep, SecurityEncoder securityEncoder) {
        this.userRep = userRep;
        this.municipalityRep = municipalityRep;
        this.securityEncoder = securityEncoder;
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
        targetUser.setPassword(user.getPassword());
        targetUser.setPatronymic(user.getPatronymic());
        targetUser.setSurname(user.getSurname());
        targetUser.setRole(user.getRole());
    }

    public void deleteUser(long id) {
        User user = userRep.findUserById(id).orElseThrow(ResourceNotFound::new);
        userRep.delete(user);
    }

    //-------------------------------------JUST_TEST-------------------------------------

    public void insertData() {
        log.info("> Inserting data...");
        userRep.save(new UserBuilder().withLogin("edit").withPassword(securityEncoder.passwordEncoder()
                .encode("edit")).withName("BestName").withSurname("BestSurname").withPatronymic("SuperPatron")
                .withMunicipality(municipalityRep.findAll()).withRole(Roles.EDITOR).build());

        userRep.save(new UserBuilder().withLogin("user").withPassword(securityEncoder.passwordEncoder()
                .encode("user")).withName("BestName").withSurname("BestSurname").withPatronymic("SuperPatron")
                .withMunicipality(municipalityRep.findAll()).withRole(Roles.USER).build());

        userRep.save(new UserBuilder().withLogin("admin").withPassword(securityEncoder.passwordEncoder()
                .encode("admin")).withName("name").withSurname("sur").withPatronymic("patron")
                .withMunicipality(municipalityRep.findAll()).withRole(Roles.ADMIN).build());
    }
}
