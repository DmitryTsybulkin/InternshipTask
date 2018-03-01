package com.pany.adv.advtask.dto;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;

public class PhotoDTO extends Photo {
    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public Request getRequest() {
        return super.getRequest();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }
}
