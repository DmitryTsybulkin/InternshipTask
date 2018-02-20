package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.ConstructionRequest;
import com.pany.adv.advtask.repository.ConstructionRequestRep;
import com.pany.adv.advtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConstructionRequestService {

    @Autowired
    private ConstructionRequestRep requestToAddAdConstr;

    @Autowired
    private UserRepository userRepository;

    public void insertData() {
        requestToAddAdConstr.save(new ConstructionRequest(new Date(), userRepository.findOne(1L), "on", 5, 4, userRepository.findOne(1L), new Date(), 5, "adf", "das"));
    }
}
