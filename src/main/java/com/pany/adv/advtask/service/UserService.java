package com.pany.adv.advtask.service;

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

    public void insertData() {
        log.info("> Inserting data...");
        String password = "epicPassword";
        userRep.save(new UserBuilder().withLogin("BestLogin").withPassword(securityEncoder.passwordEncoder().
                encode(password)).withName("BestName").withSurname("BestSurname").withPatronymic("SuperPatron")
                .withMunicipality(municipalityRep.findAll()).withAdmin(true).withEditor(true).build());
        log.info("> Done.");
    }

    public List<User> findAll() {
        return userRep.findAll();
    }

}
