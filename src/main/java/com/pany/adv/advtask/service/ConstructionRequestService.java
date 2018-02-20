package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.ConstructionRequest;
import com.pany.adv.advtask.repository.RequesToAddAdConstr;
import com.pany.adv.advtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ConstructionRequestService {

    @Autowired
    RequesToAddAdConstr requestToAddAdConstr;

    @Autowired
    UserRepository userRepository;

    public void insertData() {
        requestToAddAdConstr.save(new ConstructionRequest(new Date(), userRepository.findOne(1L), "on", 5, 4, userRepository.findAll(), new Date(), 5, "adf", "das"));
    }
}
