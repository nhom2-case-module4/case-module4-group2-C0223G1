package com.example.project_book.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    @Column(columnDefinition = "longtext",nullable = false)
    private String img;
    @Column(nullable = false)
    private String nameProduct;
    @Column(nullable = false)
    private String publicationYear;
    @Column(nullable = false)
    private String author;
    @Column(columnDefinition = "longtext",nullable = true)
    private String describeBook;
    @Column(nullable = false)
    private String nationBook;
    @Column(nullable = false)
    private String publishingCompany;
    @Column(nullable = false)
    private int quantityBooks;
    @Column(nullable = false)
    private double priceBook;
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "id_type_product",nullable = false)
    private RoleUser roleUser;

    public Product() {
    }

    public Product(int idProduct, String img, String nameProduct, String publicationYear, String author, String describeBook, String nationBook, String publishingCompany, int quantityBooks, double priceBook, boolean isDelete, RoleUser roleUser) {
        this.idProduct = idProduct;
        this.img = img;
        this.nameProduct = nameProduct;
        this.publicationYear = publicationYear;
        this.author = author;
        this.describeBook = describeBook;
        this.nationBook = nationBook;
        this.publishingCompany = publishingCompany;
        this.quantityBooks = quantityBooks;
        this.priceBook = priceBook;
        this.isDelete = isDelete;
        this.roleUser = roleUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescribeBook() {
        return describeBook;
    }

    public void setDescribeBook(String describeBook) {
        this.describeBook = describeBook;
    }

    public String getNationBook() {
        return nationBook;
    }

    public void setNationBook(String nationBook) {
        this.nationBook = nationBook;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public int getQuantityBooks() {
        return quantityBooks;
    }

    public void setQuantityBooks(int quantityBooks) {
        this.quantityBooks = quantityBooks;
    }

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public RoleUser getTypeProduct() {
        return roleUser;
    }

    public void setTypeProduct(RoleUser typeProduct) {
        this.roleUser = typeProduct;
    }
}
