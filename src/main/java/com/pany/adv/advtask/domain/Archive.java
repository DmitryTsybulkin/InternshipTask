package com.pany.adv.advtask.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "archive")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Request requestId;

    @OneToOne(fetch = FetchType.EAGER)
    private AdvPlace advPlace;

    @OneToOne(fetch = FetchType.EAGER)
    private AdvConstruction advConstruction;

    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private User applicant;

    @ManyToOne
    private User handler;

    @Column(name = "processed_on")
    @Temporal(TemporalType.DATE)
    private Date dateProcessed;

    @Column(name = "version")
    private String version;

    @Column(name = "reason")
    private String reason;

    @Column(name = "relevance")
    private String actuality;

    public Archive() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Request getRequestId() {
        return requestId;
    }

    public void setRequestId(Request requestId) {
        this.requestId = requestId;
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
        return "Archive{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", advPlace=" + advPlace +
                ", advConstruction=" + advConstruction +
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
