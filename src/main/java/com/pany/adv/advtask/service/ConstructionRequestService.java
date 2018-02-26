package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.ConstructionRequest;
import com.pany.adv.advtask.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConstructionRequestService {

    @Autowired
    private ConstructionRequestRep requestToAddAdConstr;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertisementConstructionRep constructionRep;

    @Autowired
    private AdvertisementPlaceRep placeRep;

    @Autowired
    private PhotoRep photoRep;

    public void insertData() {
        requestToAddAdConstr.save(new ConstructionRequest(new Date(), userRepository.findOne(1L), "on",
                placeRep.findOne(1L), constructionRep.findOne(1L), userRepository.findOne(1L), new Date(),
                "5", "adf", "das", photoRep.findOne(1L)));
    }
}
