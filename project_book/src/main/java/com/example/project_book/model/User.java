package com.example.project_book.model;

import com.example.project_book.model.RoleUser;
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
    @Column(nullable = false)
    private String genderUser;
    @Column(columnDefinition = "longtext",nullable = false)
    @JsonIgnore
    private String passUser;
    @ManyToOne
    @JoinColumn(name="idType",nullable = false)
    private RoleUser usersType;

    private boolean isDelete =false;
    public User() {
    }

    public User(int id, String name, String phoneNumber, String dateOfBirth, String email, String gender, String pass, RoleUser usersType, boolean flagDelete) {
        this.idUser = id;
        this.name = name;
        this.phone = phoneNumber;
        this.birthOfDay = dateOfBirth;
        this.emailUser = email;
        this.genderUser = gender;
        this.passUser = pass;
        this.usersType = usersType;
        this.isDelete =flagDelete;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int id) {
        this.idUser = id;
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

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getBirthOfDay() {
        return birthOfDay;
    }

    public void setBirthOfDay(String dateOfBirth) {
        this.birthOfDay = dateOfBirth;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String email) {
        this.emailUser = email;
    }

    public String getGenderUser() {
        return genderUser;
    }

    public void setGenderUser(String gender) {
        this.genderUser = gender;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String pass) {
        this.passUser = pass;
    }

    public RoleUser getUsersType() {
        return usersType;
    }

    public void setUsersType(RoleUser usersType) {
        this.usersType = usersType;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean flagDelete) {
        this.isDelete = flagDelete;
    }
}
