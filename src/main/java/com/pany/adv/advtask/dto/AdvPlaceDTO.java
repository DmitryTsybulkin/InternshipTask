package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Municipality;

public class AdvPlaceDTO {

    public long id;
    public String owner;
    public String address;
    public MunicipalityDTO municipalityDTO;

    public long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public MunicipalityDTO getMunicipalityDTO() {
        return municipalityDTO;
    }
}
