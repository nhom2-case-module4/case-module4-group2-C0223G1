package com.example.project_book.dto.dto_users;

import com.example.project_book.model.RoleUser;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UsersDto implements Validator {

    private int idUser;

    @Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", message = "Họ tên không hợp lệ")
    private String name;
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    private String birthOfDay;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không hợp lệ")
    private String emailUser;
    @Pattern(regexp = "^(Nam|Nữ|Khác)$", message = "Giới tính không hợp lệ")
    private String genderUser;
    @NotBlank
    private String passUser;

    private RoleUser roleUser;
    private boolean isDelete=false;

    public UsersDto() {
    }

    public UsersDto(int idUser, String name, String phone, String birthOfDay, String emailUser, String genderUser, String passUser, RoleUser roleUser, boolean isDelete) {
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

    public String getGenderUser() {
        return genderUser;
    }

    public void setGenderUser(String genderUser) {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
