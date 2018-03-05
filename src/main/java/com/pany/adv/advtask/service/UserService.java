package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.exceptions.ExceptionAPI;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        userRep.save(user);
    }

    public User findById(long id) {
        User user = userRep.findOne(id);
        if (user == null) {
            throw new ExceptionAPI("user with id: " + id + " not found. \n" + HttpStatus.NOT_FOUND.getReasonPhrase() + "\n" + HttpStatus.NOT_FOUND.value());
        }
        return user;
    }

    public List<User> findAll() {
        return userRep.findAll();
    }

    @Transactional
    public void updateUser(long id, User user) {
        User targetUser = findById(id);
        targetUser.setLogin(user.getLogin());
        targetUser.setAdmin(user.isAdmin());
        targetUser.setEditor(user.isEditor());
        targetUser.setMunicipality(user.getMunicipality());
        targetUser.setName(user.getName());
        targetUser.setPassword(user.getPassword());
        targetUser.setPatronymic(user.getPatronymic());
        targetUser.setSurname(user.getSurname());
    }

    public void deleteUser(long id) {
        userRep.delete(id);
    }

    //-------------------------------------JUST_TEST-------------------------------------

    public void insertData() {
        log.info("> Inserting data...");
        String password = "epicPassword";
        userRep.save(new UserBuilder().withLogin("BestLogin").withPassword(securityEncoder.passwordEncoder().
                encode(password)).withName("BestName").withSurname("BestSurname").withPatronymic("SuperPatron")
                .withMunicipality(municipalityRep.findAll()).withAdmin(true).withEditor(true).build());
        log.info("> Done.");
    }
}
