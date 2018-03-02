package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.domain.builders.UserBuilder;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userRep.findOne(id);
    }

    public User findByLogin(String login) {
        return userRep.findUserByLogin(login);
    }

    public User findByName(String name) {
        return userRep.findByName(name);
    }

    public User findBySurname(String surname) {
        return userRep.findBySurname(surname);
    }

    // is right?
    public User findByMunicipalityContains(List<Municipality> municipality) {
        return userRep.findByMunicipalityContains(municipality);
    }

    public User findByEditorIsTrue() {
        return userRep.findByEditorIsTrue();
    }

    public List<User> findAll() {
        return userRep.findAll();
    }

    public void updateUser(long id, User user) {
        this.findById(id).setLogin(user.getLogin());
        this.findById(id).setAdmin(user.isAdmin());
        this.findById(id).setEditor(user.isEditor());
        this.findById(id).setMunicipality(user.getMunicipality());
        this.findById(id).setName(user.getName());
        this.findById(id).setPassword(user.getPassword());
        this.findById(id).setPatronymic(user.getPatronymic());
        this.findById(id).setSurname(user.getSurname());
    }

    public void updateUserName(long id, String name) {
        this.findById(id).setName(name);
    }

    public void updateUserLogin(long id, String login) {
        this.findById(id).setLogin(login);
    }

    public void updateUserAdmin(long id, boolean isAdmin) {
        this.findById(id).setAdmin(isAdmin);
    }
    
    public void updateUserEditor(long id, boolean isEditor) {
        this.findById(id).setEditor(isEditor);
    }

    public void updateUserMunicipality(long id, List<Municipality> municipalities) {
        this.findById(id).setMunicipality(municipalities);
    }

    public void updateUserPassword(long id, String password) {
        this.findById(id).setPassword(securityEncoder.passwordEncoder().encode(password));
    }

    public void updateUserPatronymic(long id, String patronymic) {
        this.findById(id).setPatronymic(patronymic);
    }

    public void updateUserSurname(long id, String surname) {
        this.findById(id).setSurname(surname);
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
