package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.ConstructionRequestRep;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConstructionRequestRep constructionRequestRep;

    @Autowired
    MunicipalityRep municipalityRep;

    private SecurityEncoder securityEncoder; // not work

    private String password = "epicPassword";

    public void insertData() {
        log.info("> Inserting data...");
        userRepository.save(new User("BestLogin", "stupidPassword",
                "BestName", "BestSurname", "SuperPatron", "RegularRole",
                municipalityRep.findAll(), true, true));
        log.info("> Done.");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
