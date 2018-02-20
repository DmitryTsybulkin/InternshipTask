package com.pany.adv.advtask.service;

//import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.domain.User;
//import com.pany.adv.advtask.repository.MunicipalityRepository;
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

//    @Autowired
//    MunicipalityRepository municipalityRepository;

    public void insertData() {
        log.info("> Inserting data...");
        //municipalityRepository.save(new Municipality("bestMunicipality"));  // add municipality
        userRepository.save(new User("BestLogin", "password", "BestUser", "BestSurname",
                "BestPatron", "BestRole", true, true));
        userRepository.save(new User("fsd", "fsd", "das", "fsd", "fsd", "fsd", true, true));
        log.info("> Done.");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
