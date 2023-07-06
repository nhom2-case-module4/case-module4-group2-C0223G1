package com.example.project_book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUser;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;
    private String birthOfDay;
    @Column(columnDefinition = "longtext",nullable = false)
    private String emailUser;
    @Column(nullable = true)
    private Boolean genderUser;
    @Column(columnDefinition = "longtext",nullable = false)
    @JsonIgnore
    private String passUser;
    @ManyToOne
    @JoinColumn(name="id_role",nullable = false)
    private RoleUser roleUser;

    private boolean isDelete =false;
    public User() {
    }

    public User(int idUser, String name, String phone, String birthOfDay, String emailUser, Boolean genderUser, String passUser, RoleUser roleUser, boolean isDelete) {
        this.idUser = idUser;
        this.name = name;
        this.phone = phone;
        this.birthOfDay = birthOfDay;
        this.emailUser = emailUser;
        this.genderUser = genderUser;
        this.passUser = passUser;
        this.roleUser = roleUser;
        this.isDelete = isDelete;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthOfDay() {
        return birthOfDay;
    }

    public void setBirthOfDay(String birthOfDay) {
        this.birthOfDay = birthOfDay;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Boolean getGenderUser() {
        return genderUser;
    }

    public void setGenderUser(Boolean genderUser) {
        this.genderUser = genderUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
