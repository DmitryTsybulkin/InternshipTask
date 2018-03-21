package com.pany.adv.advtask.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pany.adv.advtask.domain.Request;

import java.util.Date;

public class ArchiveDTO {

    public long id;
    public RequestDTO requestId;
    public AdvPlaceDTO place;
    public AdvConstructionDTO advConstruction;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;
    public String applicant;
    public String handler;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date dateProcessed;
    public int version;
    public String reason;
    public String actuality;

}
