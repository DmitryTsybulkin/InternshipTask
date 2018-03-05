package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.domain.Photo;

import java.util.Date;

public class RequestDTO {

    public long id;
    public Date date;
    public String applicant;
    public String status;
    public AdvPlace place;
    public AdvConstruction advConstruction;
    public String handler;
    public Date dateProcessed;
    public String version;
    public String reason;
    public String actuality;
    public Photo photo;

}
