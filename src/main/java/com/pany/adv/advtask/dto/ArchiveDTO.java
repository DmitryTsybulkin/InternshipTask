package com.pany.adv.advtask.dto;


import com.pany.adv.advtask.domain.Request;

import java.util.Date;

public class ArchiveDTO {

    public long id;
    public RequestDTO requestId;
    public AdvPlaceDTO place;
    public AdvConstructionDTO advConstruction;
    public Date date;
    public String applicant;
    public String handler;
    public Date dateProcessed;
    public String version;
    public String reason;
    public String actuality;

}
