package com.pany.adv.advtask.domain.builders;

import com.pany.adv.advtask.domain.*;

import java.util.Date;

public final class ArchiveBuilder {
    private Request requestId;
    private AdvPlace advPlace;
    private AdvConstruction advConstruction;
    private Date date;
    private User applicant;
    private User handler;
    private Date dateProcessed;
    private String version;
    private String reason;
    private String actuality;

    private ArchiveBuilder() {
    }

    public static ArchiveBuilder anArchive() {
        return new ArchiveBuilder();
    }

    public ArchiveBuilder withRequestId(Request requestId) {
        this.requestId = requestId;
        return this;
    }

    public ArchiveBuilder withAdvPlace(AdvPlace advPlace) {
        this.advPlace = advPlace;
        return this;
    }

    public ArchiveBuilder withAdvConstruction(AdvConstruction advConstruction) {
        this.advConstruction = advConstruction;
        return this;
    }

    public ArchiveBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public ArchiveBuilder withApplicant(User applicant) {
        this.applicant = applicant;
        return this;
    }

    public ArchiveBuilder withHandler(User handler) {
        this.handler = handler;
        return this;
    }

    public ArchiveBuilder withDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
        return this;
    }

    public ArchiveBuilder withVersion(String version) {
        this.version = version;
        return this;
    }

    public ArchiveBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public ArchiveBuilder withActuality(String actuality) {
        this.actuality = actuality;
        return this;
    }

    public Archive build() {
        Archive archive = new Archive();
        archive.setRequestId(requestId);
        archive.setAdvPlace(advPlace);
        archive.setAdvConstruction(advConstruction);
        archive.setDate(date);
        archive.setApplicant(applicant);
        archive.setHandler(handler);
        archive.setDateProcessed(dateProcessed);
        archive.setVersion(version);
        archive.setReason(reason);
        archive.setActuality(actuality);
        return archive;
    }
}
