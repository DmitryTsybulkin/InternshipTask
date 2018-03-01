package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.domain.Municipality;

public class PlaceDTO extends AdvPlace {
    @Override
    public String getOwner() {
        return super.getOwner();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public Municipality getMunicipality() {
        return super.getMunicipality();
    }
}
