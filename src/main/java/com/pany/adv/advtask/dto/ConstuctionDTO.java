package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.domain.AdvPlace;

import java.util.Date;

public class ConstuctionDTO extends AdvConstruction {
    @Override
    public AdvPlace getAdvPlaceId() {
        return super.getAdvPlaceId();
    }

    @Override
    public String getOwner() {
        return super.getOwner();
    }

    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public Date getDate() {
        return super.getDate();
    }

    @Override
    public long getId() {
        return super.getId();
    }
}
