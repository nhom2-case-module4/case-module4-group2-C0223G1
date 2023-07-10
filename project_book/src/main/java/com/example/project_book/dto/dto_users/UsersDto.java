package com.example.project_book.dto.dto_users;

import com.example.project_book.model.RoleUser;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsersDto implements Validator {

    private int idUser;
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @NotBlank(message = "First and last name cannoorder_bookt be left blank")
    @Size(min = 2,max = 50,message = "Up to 50 characters")
    @Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", message = "Invalid first and last name!")
    private String name;
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @NotBlank(message = "Phone cannot be left blank")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number!")
    private String phone;
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @NotBlank(message = "Cannot be left blank")
    private String birthOfDay;
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @NotBlank(message = "Email cannot be left blank")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email!")
    private String emailUser;

    //    Create: Huynh Duc
//    Day: 06/07/2023
    //    @Pattern(regexp = "^(Nam|Nữ|Khác)$", message = "Giới tính không hợp lệ")
    private Boolean genderUser;
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @NotBlank(message = "Cannot be left blank")
    @Size(min = 6,max = 45,message = "Up to 50 characters, min 6 character")
    private String passUser;

    private RoleUser roleUser;
    private boolean isDelete = false;

    public UsersDto() {
    }

    public UsersDto(int idUser, String name, String phone, String birthOfDay, String emailUser, Boolean genderUser, String passUser, RoleUser roleUser, boolean isDelete) {
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

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    public Boolean getGenderUser() {
        return genderUser;
    }

    //    Create: Huynh Duc
    //    Day: 06/07/2023
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
