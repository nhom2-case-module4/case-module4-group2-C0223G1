package com.example.project_book.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_book")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    @Column(nullable = false)
    private LocalDate dayOrder;
    private LocalDate dayTake;
    @Column(columnDefinition = "longtext",nullable = false)
    private String addressPeople;
    @Column(columnDefinition = "longtext",nullable = true)
    private String noteOrder;
    private boolean flagDelete;
    @ManyToOne
    @JoinColumn(name = "status_id_status", columnDefinition = "int default 1")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

    public Order() {
    }

    public Order(int idOrder, LocalDate dayOrder, LocalDate dayTake, String addressPeople, String noteOrder, boolean flagDelete, Status status, User user) {
        this.idOrder = idOrder;
        this.dayOrder = dayOrder;
        this.dayTake = dayTake;
        this.addressPeople = addressPeople;
        this.noteOrder = noteOrder;
        this.flagDelete = flagDelete;
        this.status = status;
        this.user = user;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
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

    public String getAddressPeople() {
        return addressPeople;
    }

    public void setAddressPeople(String addressPeople) {
        this.addressPeople = addressPeople;
    }

    public String getNoteOrder() {
        return noteOrder;
    }

    public void setNoteOrder(String noteOrder) {
        this.noteOrder = noteOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
