package com.pany.adv.advtask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.domain.Photo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestDTO {

    public long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    public Date date;
    public String applicant;
    public String status;
    public AdvPlaceDTO place;
    public AdvConstructionDTO advConstruction;
    public String handler;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    public Date dateProcessed;
    public int version;
    public String reason;
    public String actuality;
    public PhotoDTO photo;

}
