package com.pany.adv.advtask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AdvConstructionDTO {

    public long id;
    public long place;
    public String owner;
    public int number;
    public String type;
    public String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date date;

}
