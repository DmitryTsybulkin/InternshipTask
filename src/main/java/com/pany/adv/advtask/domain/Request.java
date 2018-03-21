package com.pany.adv.advtask.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_on")
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @ManyToOne
    private User applicant; //1:n

    @Column(name = "status")
    private String status;

    @OneToOne
    private AdvPlace advPlace; //1:1 by id

    @OneToOne
    private AdvConstruction advConstruction; //1:1 by id
    
    @ManyToOne
    private User handler; //n:1

    @Column(name = "processed_on")
    @Temporal(value = TemporalType.DATE)
    private Date dateProcessed;

    @Column(name = "version")
    private int version;

    @Column(name = "reason")
    private String reason;

    @Column(name = "relevance")
    private String actuality;

    @JsonIgnore
    @OneToOne
    private Photo photo;

    public Request() {}

    //Getters & Setters

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

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AdvPlace getAdvPlace() {
        return advPlace;
    }

    public void setAdvPlace(AdvPlace advPlace) {
        this.advPlace = advPlace;
    }

    public AdvConstruction getAdvConstruction() {
        return advConstruction;
    }

    public void setAdvConstruction(AdvConstruction advConstruction) {
        this.advConstruction = advConstruction;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", applicant=" + applicant +
                ", status='" + status + '\'' +
                ", advPlace=" + advPlace +
                ", advConstruction=" + advConstruction +
                ", handler=" + handler +
                ", dateProcessed=" + dateProcessed +
                ", version=" + version +
                ", reason='" + reason + '\'' +
                ", actuality='" + actuality + '\'' +
                '}';
    }

}
