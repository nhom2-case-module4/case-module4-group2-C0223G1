package com.example.project_book.model;

import javax.persistence.*;

@Entity
@Table(name = "status_order")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatus;
    private  String nameStatus;

    public Status() {
    }

    public Status(int idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
