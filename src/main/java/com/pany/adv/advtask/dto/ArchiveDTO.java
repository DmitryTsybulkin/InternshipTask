package com.pany.adv.advtask.dto;


import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.domain.Request;

import java.util.Date;

public class ArchiveDTO {

    public long id;
    public Request requestId;
    public AdvPlace place;
    public AdvConstruction advConstruction;
    public Date date;
    public String applicant;
    public String handler;
    public Date dateProcessed;
    public String version;
    public String reason;
    public String actuality;

}
