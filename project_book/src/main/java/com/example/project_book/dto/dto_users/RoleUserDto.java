package com.example.project_book.dto.dto_users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RoleUserDto {
    private int idRole;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "tên không hợp lệ")
    private String nameRole;

    private boolean isDelete=false;

    public RoleUserDto() {
    }

    public RoleUserDto(int idRole, String nameRole, boolean isDelete) {
        this.idRole = idRole;
        this.nameRole = nameRole;
        this.isDelete = isDelete;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
