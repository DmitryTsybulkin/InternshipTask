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

    @Column(name = "file_name")
    private String fileName;

    public Photo(Request request, String fileName) {
        this.request = request;
        this.fileName = fileName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", request=" + request +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
