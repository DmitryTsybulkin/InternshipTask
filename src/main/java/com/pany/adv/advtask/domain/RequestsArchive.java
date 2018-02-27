package com.pany.adv.advtask.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "archive")
public class RequestsArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ConstructionRequest requestId;

    @OneToOne(fetch = FetchType.EAGER)
    private AdvertisementPlace advertisementPlace;

    @OneToOne(fetch = FetchType.EAGER)
    private AdvertisementConstruction advertisementConstruction;

    @Column(name = "created_on")
    private Date date;

    @ManyToOne
    private User applicant;

    @ManyToOne
    private User handler;

    @Column(name = "processed_on")
    private Date dateProcessed;

    @Column(name = "version")
    private String version;

    @Column(name = "reason")
    private String reason;

    @Column(name = "relevance")
    private String actuality;

    public RequestsArchive(ConstructionRequest requestId, AdvertisementPlace advertisementPlace,
                           AdvertisementConstruction advertisementConstruction, Date date, User applicant,
                           User handler, Date dateProcessed, String version, String reason, String actuality) {
        this.requestId = requestId;
        this.advertisementPlace = advertisementPlace;
        this.advertisementConstruction = advertisementConstruction;
        this.date = date;
        this.applicant = applicant;
        this.handler = handler;
        this.dateProcessed = dateProcessed;
        this.version = version;
        this.reason = reason;
        this.actuality = actuality;
    }

    public RequestsArchive() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConstructionRequest getRequestId() {
        return requestId;
    }

    public void setRequestId(ConstructionRequest requestId) {
        this.requestId = requestId;
    }

    public AdvertisementPlace getAdvertisementPlace() {
        return advertisementPlace;
    }

    public void setAdvertisementPlace(AdvertisementPlace advertisementPlace) {
        this.advertisementPlace = advertisementPlace;
    }

    public AdvertisementConstruction getAdvertisementConstruction() {
        return advertisementConstruction;
    }

    public void setAdvertisementConstruction(AdvertisementConstruction advertisementConstruction) {
        this.advertisementConstruction = advertisementConstruction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public User getHandler() {
        return handler;
    }

    public void setHandler(User handler) {
        this.handler = handler;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getActuality() {
        return actuality;
    }

    public void setActuality(String actuality) {
        this.actuality = actuality;
    }

    @Override
    public String toString() {
        return "RequestsArchive{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", advertisementPlace=" + advertisementPlace +
                ", advertisementConstruction=" + advertisementConstruction +
                ", date=" + date +
                ", applicant=" + applicant +
                ", handler=" + handler +
                ", dateProcessed=" + dateProcessed +
                ", version='" + version + '\'' +
                ", reason='" + reason + '\'' +
                ", actuality='" + actuality + '\'' +
                '}';
    }
}
