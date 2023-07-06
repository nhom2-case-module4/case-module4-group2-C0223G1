package com.example.project_book.model;

import javax.persistence.*;

@Entity
@Table(name = "type_product")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_type_product")
    private int idProduct;
    @Column(columnDefinition = "longtext",nullable = false,name = "name_type_product")
    private String nameTypeProduct;

    public TypeProduct() {
    }

    public TypeProduct(int idProduct, String nameTypeProduct) {
        this.idProduct = idProduct;
        this.nameTypeProduct = nameTypeProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameTypeProduct() {
        return nameTypeProduct;
    }

    public void setNameTypeProduct(String nameTypeProduct) {
        this.nameTypeProduct = nameTypeProduct;
    }
}
