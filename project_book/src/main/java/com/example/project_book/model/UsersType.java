package com.example.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name="UserTypes")
public class UsersType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    private boolean flagDelete=false;

    public UsersType() {
    }

    public UsersType(int id, String name, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.flagDelete=flagDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

}
