package com.example.project_book.model;

import javax.persistence.*;

@Entity
@Table(name="role_user")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private int idRole;

    private String nameRole;



    public RoleUser() {
    }

    public RoleUser(int id, String name ) {
        this.idRole = id;
        this.nameRole = name;

    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int id) {
        this.idRole = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String name) {
        this.nameRole = name;
    }

}
