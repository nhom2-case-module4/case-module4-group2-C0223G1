package com.example.project_book.dto.dto_users;

import com.example.project_book.model.UsersType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UsersDto implements Validator {

    private int id;

    @Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", message = "Họ tên không hợp lệ")
    private String name;
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    private String dateOfBirth;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không hợp lệ")
    private String email;
    @Pattern(regexp = "^(Nam|Nữ|Khác)$", message = "Giới tính không hợp lệ")
    private String gender;
    @NotBlank
    private String pass;

    private UsersType usersType;
    private boolean flagDelete=false;

    public UsersDto() {
    }

    public UsersDto(int id, String name, String phoneNumber, String dateOfBirth, String email, String gender, String pass, UsersType usersType, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.pass = pass;
        this.usersType = usersType;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UsersType getUsersType() {
        return usersType;
    }

    public void setUsersType(UsersType usersType) {
        this.usersType = usersType;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
