package com.pany.adv.advtask.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class ConstructionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_on")
    private Date date;

    @ManyToOne
    private User applicant; //1:n

    @Column(name = "status")
    private String status;

    @OneToOne
    private AdvertisementPlace advertisementPlace; //1:1 by id

    @OneToOne
    private AdvertisementConstruction advertisementConstruction; //1:1 by id
    
    @ManyToOne
    private User handler; //n:1

    @Column(name = "processed_on")
    private Date dateProcessed;

    @Column(name = "version")
    private String version;

    @Column(name = "reason")
    private String reason;

    @Column(name = "relevance")
    private String actuality;

    @OneToOne
    private Photo photoId;

    public ConstructionRequest() {}

    public ConstructionRequest(Date date, User applicant, String status, AdvertisementPlace advertisementPlace, AdvertisementConstruction advertisementConstruction,
                               User handler, Date dateProcessed, String version, String reason, String actuality, Photo photoId) {
        this.date = date;
        this.applicant = applicant;
        this.status = status;
        this.advertisementPlace = advertisementPlace;
        this.advertisementConstruction = advertisementConstruction;
        this.handler = handler;
        this.dateProcessed = dateProcessed;
        this.version = version;
        this.reason = reason;
        this.actuality = actuality;
        this.photoId = photoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return applicant;
    }

    public void setUser(User applicant) {
        this.applicant = applicant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AdvertisementPlace getAdPlace() {
        return advertisementPlace;
    }

    public void setAdPlace(AdvertisementPlace advertisementPlace) {
        this.advertisementPlace = advertisementPlace;
    }

    public AdvertisementConstruction getAdConstruction() {
        return advertisementConstruction;
    }

    public void setAdConstruction(AdvertisementConstruction advertisementConstruction) {
        this.advertisementConstruction = advertisementConstruction;
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

    public String getSign() {
        return actuality;
    }

    public void setSign(String actuality) {
        this.actuality = actuality;
    }

    @Override
    public String toString() {
        return "ConstructionRequest{" +
                "id=" + id +
                ", date=" + date +
                ", applicant=" + applicant +
                ", status='" + status + '\'' +
                ", advertisementPlace=" + advertisementPlace +
                ", advertisementConstruction=" + advertisementConstruction +
                ", handler=" + handler +
                ", dateProcessed=" + dateProcessed +
                ", version=" + version +
                ", reason='" + reason + '\'' +
                ", actuality='" + actuality + '\'' +
                '}';
    }

    public Photo getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Photo photoId) {
        this.photoId = photoId;
    }
}
