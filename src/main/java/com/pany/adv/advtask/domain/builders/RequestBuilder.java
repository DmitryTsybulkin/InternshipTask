package com.pany.adv.advtask.domain.builders;

import com.pany.adv.advtask.domain.*;

import java.util.Date;

public final class RequestBuilder {
    private Date date;
    private User applicant; //1:n
    private String status;
    private AdvPlace advPlace; //1:1 by id
    private AdvConstruction advConstruction; //1:1 by id
    private User handler; //n:1
    private Date dateProcessed;
    private int version;
    private String reason;
    private String actuality;
    private Photo photo;

    public RequestBuilder() {
    }

    public static RequestBuilder aRequest() {
        return new RequestBuilder();
    }

    public RequestBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public RequestBuilder withApplicant(User applicant) {
        this.applicant = applicant;
        return this;
    }

    public RequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public RequestBuilder withAdvPlace(AdvPlace advPlace) {
        this.advPlace = advPlace;
        return this;
    }

    public RequestBuilder withAdvConstruction(AdvConstruction advConstruction) {
        this.advConstruction = advConstruction;
        return this;
    }

    public RequestBuilder withHandler(User handler) {
        this.handler = handler;
        return this;
    }

    public RequestBuilder withDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
        return this;
    }

    public RequestBuilder withVersion(int version) {
        this.version = version;
        return this;
    }

    public RequestBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public RequestBuilder withActuality(String actuality) {
        this.actuality = actuality;
        return this;
    }

    public RequestBuilder withPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    public Request build() {
        Request request = new Request();
        request.setDate(date);
        request.setApplicant(applicant);
        request.setStatus(status);
        request.setAdvPlace(advPlace);
        request.setAdvConstruction(advConstruction);
        request.setHandler(handler);
        request.setDateProcessed(dateProcessed);
        request.setVersion(version);
        request.setReason(reason);
        request.setActuality(actuality);
        request.setPhoto(photo);
        return request;
    }
}
