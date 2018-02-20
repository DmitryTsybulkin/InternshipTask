package com.pany.adv.advtask.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_to_add_advertisement_construction")
public class ConstructionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_on")
    private Date date;

    @ManyToOne
    //@Column(name = "applicant")
    private User user; //1:n

    @Column(name = "status")
    private String status;

    @Column(name = "data_of_advertisement_place")
    private int adPlace; //1:1 by id

    @Column(name = "data_of_advertisement_construction")
    private int adConstruction; //1:1 by id

    //@Column(name = "handler")
    @ManyToOne
    private User handler; //1:n

    @Column(name = "date_of_processed")
    private Date dateProcessed;

    @Column(name = "version")
    private int version;

    @Column(name = "reason_for_rejection")
    private String reason;

    @Column(name = "sign_of_relevance")
    private String sign;

    public ConstructionRequest() {}

    public ConstructionRequest(Date date, User user, String status, int adPlace, int adConstruction,
                               User handler, Date dateProcessed, int version, String reason, String sign) {
        this.date = date;
        this.user = user;
        this.status = status;
        this.adPlace = adPlace;
        this.adConstruction = adConstruction;
        this.handler = handler;
        this.dateProcessed = dateProcessed;
        this.version = version;
        this.reason = reason;
        this.sign = sign;
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
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAdPlace() {
        return adPlace;
    }

    public void setAdPlace(int adPlace) {
        this.adPlace = adPlace;
    }

    public int getAdConstruction() {
        return adConstruction;
    }

    public void setAdConstruction(int adConstruction) {
        this.adConstruction = adConstruction;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "ConstructionRequest{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", status='" + status + '\'' +
                ", adPlace=" + adPlace +
                ", adConstruction=" + adConstruction +
                ", handler=" + handler +
                ", dateProcessed=" + dateProcessed +
                ", version=" + version +
                ", reason='" + reason + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

}
