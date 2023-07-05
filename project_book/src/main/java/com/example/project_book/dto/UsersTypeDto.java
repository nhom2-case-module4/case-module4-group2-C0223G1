package com.example.bookstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UsersTypeDto {
    private int id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "tên không hợp lệ")
    private String name;

    private boolean flagDelete=false;

    public UsersTypeDto() {
    }

    public UsersTypeDto(int id, String name, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.flagDelete = flagDelete;
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
