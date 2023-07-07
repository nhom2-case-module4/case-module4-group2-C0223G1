package com.example.project_book.dto.dto_product;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class TypeProductDto implements Validator {

    private int idTypeProduct;
    @NotBlank(message = "Type Product cannot be null")
    private String nameTypeProduct;

    public TypeProductDto() {
    }

    public TypeProductDto(int idTypeProduct, String nameTypeProduct) {
        this.idTypeProduct = idTypeProduct;
        this.nameTypeProduct = nameTypeProduct;
    }

    public int getIdTypeProduct() {
        return idTypeProduct;
    }

    public void setIdTypeProduct(int idTypeProduct) {
        this.idTypeProduct = idTypeProduct;
    }

    public String getNameTypeProduct() {
        return nameTypeProduct;
    }

    public void setNameTypeProduct(String nameTypeProduct) {
        this.nameTypeProduct = nameTypeProduct;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
