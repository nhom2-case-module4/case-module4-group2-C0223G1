package com.example.project_book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private LocalDate birthOfDay;
    @Column(columnDefinition = "longtext",nullable = false)
    private String emailUser;
    @Column(nullable = false)
    private boolean genderUser;
    @Column(columnDefinition = "longtext",nullable = false)
    @JsonIgnore
    private String passUser;
    private boolean isDelete;
    @ManyToOne
    private RoleUser roleUser;

    public User() {
    }

    public User(int idUser, String phone, LocalDate birthOfDay, String emailUser, boolean genderUser, String passUser, boolean isDelete, RoleUser roleUser) {
        this.idUser = idUser;
        this.phone = phone;
        this.birthOfDay = birthOfDay;
        this.emailUser = emailUser;
        this.genderUser = genderUser;
        this.passUser = passUser;
        this.isDelete = isDelete;
        this.roleUser = roleUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthOfDay() {
        return birthOfDay;
    }

    public void setBirthOfDay(LocalDate birthOfDay) {
        this.birthOfDay = birthOfDay;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public boolean isGenderUser() {
        return genderUser;
    }

    public void setGenderUser(boolean genderUser) {
        this.genderUser = genderUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }
}
