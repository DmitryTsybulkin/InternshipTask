package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.*;

import java.util.Date;

public class RequestDTO extends Request {

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public Date getDate() {
        return super.getDate();
    }

    @Override
    public User getApplicant() {
        return super.getApplicant();
    }

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public AdvPlace getAdvPlace() {
        return super.getAdvPlace();
    }

    @Override
    public AdvConstruction getAdvConstruction() {
        return super.getAdvConstruction();
    }

    @Override
    public User getHandler() {
        return super.getHandler();
    }

    @Override
    public Date getDateProcessed() {
        return super.getDateProcessed();
    }

    @Override
    public String getVersion() {
        return super.getVersion();
    }

    @Override
    public String getReason() {
        return super.getReason();
    }

    @Override
    public String getActuality() {
        return super.getActuality();
    }

    @Override
    public Photo getPhoto() {
        return super.getPhoto();
    }
}
