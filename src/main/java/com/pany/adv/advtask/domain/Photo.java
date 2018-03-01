package com.pany.adv.advtask.domain;

import javax.persistence.*;

@Entity
@Table(name = "photo_materials")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Request request;

    @Column(name = "file_address")
    private String address;

    public Photo(Request request, String address) {
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
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
