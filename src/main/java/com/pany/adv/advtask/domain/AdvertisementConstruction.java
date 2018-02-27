package com.pany.adv.advtask.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "constructions")
public class AdvertisementConstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    private AdvertisementPlace advertisementPlaceId;

    @Column(name = "owner")
    private String owner;

    @Column(name = "number")
    private int number;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    public AdvertisementConstruction(AdvertisementPlace advertisementPlaceId, String owner, int number,
                                     String type, String status, Date date) {
        this.advertisementPlaceId = advertisementPlaceId;
        this.owner = owner;
        this.number = number;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    public AdvertisementConstruction() {}

    public AdvertisementPlace getAdvertisementPlaceId() {
        return advertisementPlaceId;
    }

    public void setAdvertisementPlaceId(AdvertisementPlace advertisementPlaceId) {
        this.advertisementPlaceId = advertisementPlaceId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AdvertisementConstruction{" +
                "id=" + id +
                ", advertisementPlaceId=" + advertisementPlaceId +
                ", owner='" + owner + '\'' +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
