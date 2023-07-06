package com.example.project_book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class BooksOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addressPeople;
    private LocalDate dayOrder;
    private LocalDate dayTake;
    private boolean flagDelete=false;
    private String nameProduct;
    private String nameStatus;
    private String nameUser;
    private String noteOrder;
    private int numberDetail;

    public BooksOrder() {
    }

    public BooksOrder(int id, String addressPeople, LocalDate dayOrder, LocalDate dayTake, boolean flagDelete, String nameProduct, String nameStatus, String nameUser, String noteOrder, int numberDetail) {
        this.id = id;
        this.addressPeople = addressPeople;
        this.dayOrder = dayOrder;
        this.dayTake = dayTake;
        this.flagDelete = flagDelete;
        this.nameProduct = nameProduct;
        this.nameStatus = nameStatus;
        this.nameUser = nameUser;
        this.noteOrder = noteOrder;
        this.numberDetail = numberDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressPeople() {
        return addressPeople;
    }

    public void setAddressPeople(String addressPeople) {
        this.addressPeople = addressPeople;
    }

    public LocalDate getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(LocalDate dayOrder) {
        this.dayOrder = dayOrder;
    }

    public LocalDate getDayTake() {
        return dayTake;
    }

    public void setDayTake(LocalDate dayTake) {
        this.dayTake = dayTake;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNoteOrder() {
        return noteOrder;
    }

    public void setNoteOrder(String noteOrder) {
        this.noteOrder = noteOrder;
    }

    public int getNumberDetail() {
        return numberDetail;
    }

    public void setNumberDetail(int numberDetail) {
        this.numberDetail = numberDetail;
    }
}
