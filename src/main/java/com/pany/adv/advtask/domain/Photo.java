package com.pany.adv.advtask.domain;

import javax.persistence.*;

@Entity
@Table(name = "photo_materials")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private ConstructionRequest request;

    @Column(name = "file_address")
    private String address;

    public Photo(ConstructionRequest request, String address) {
        this.request = request;
        this.address = address;
    }

    public Photo() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConstructionRequest getRequest() {
        return request;
    }

    public void setRequest(ConstructionRequest request) {
        this.request = request;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", request=" + request +
                ", address='" + address + '\'' +
                '}';
    }
}
