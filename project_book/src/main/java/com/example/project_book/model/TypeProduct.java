package com.example.project_book.model;

import javax.persistence.*;

@Entity
@Table(name ="type_product")
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypeProduct;
    @Column(columnDefinition = "longtext",nullable = false)
    private String nameTypeProduct;

    public TypeProduct() {
    }

    public TypeProduct(int idTypeProduct) {
        this.idTypeProduct = idTypeProduct;
    }

    public TypeProduct(int idTypeProduct, String nameTypeProduct) {
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
}
