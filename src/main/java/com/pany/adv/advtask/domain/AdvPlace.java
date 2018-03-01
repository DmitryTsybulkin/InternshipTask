package com.pany.adv.advtask.domain;

import javax.persistence.*;

@Entity
@Table(name = "place")
public class AdvPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "address")
    private String address;

    @ManyToOne
    private Municipality municipality;

    public AdvPlace(String owner, String address, Municipality municipality) {
        this.owner = owner;
        this.address = address;
        this.municipality = municipality;
    }

    public AdvPlace() {}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }
}
