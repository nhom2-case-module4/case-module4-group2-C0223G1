package com.example.project_book.model;

import javax.persistence.*;

@Entity
public class CartOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCart;
    @Column
    private int idProduct;
    @Column
    private int quantityProduct;
    @Column
    private int idUser;

    public CartOrder() {
    }

    public CartOrder(int idProduct, int quantityProduct, int idUser) {
        this.idProduct = idProduct;
        this.quantityProduct = quantityProduct;
        this.idUser = idUser;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
