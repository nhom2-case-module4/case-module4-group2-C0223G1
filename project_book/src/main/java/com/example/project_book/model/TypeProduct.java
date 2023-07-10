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

    public TypeProduct(int idTypeProduct, String nameTypeProduct) {
        this.idProduct = idTypeProduct;
        this.nameTypeProduct = nameTypeProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idTypeProduct) {
        this.idProduct = idTypeProduct;
    }

    public String getNameTypeProduct() {
        return nameTypeProduct;
    }

    public void setNameTypeProduct(String nameTypeProduct) {
        this.nameTypeProduct = nameTypeProduct;
    }
}
