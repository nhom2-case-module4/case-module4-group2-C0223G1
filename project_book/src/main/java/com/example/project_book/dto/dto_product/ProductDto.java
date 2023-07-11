package com.example.project_book.dto.dto_product;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;


public class ProductDto implements Validator {

    private int idProduct;
    @NotBlank(message = "Image URL cannot be null")
    private String img;

    @Size(min = 2, max = 100, message = "Product nam must be >2 and <100 characters")
    private String nameProduct;
    @Max(value = 2023)
    @Pattern(regexp = "\\d{4}", message = "Publication year must be a 4-degit number")
    private String publicationYear;
    @NotBlank(message = "Author cannot be null")
    private String author;
    @NotBlank(message = "Not null")
    private String describeBook;
    @NotBlank(message = "Not null")
    private String nationBook;
    @NotBlank(message = "Not null")
    private String publishingCompany;

    @Min(value = 0, message = "Quantity books must be greater than or equal to 0")
    private int quantityBooks;

    @DecimalMin(value = "0.0", message = "Price book must be greater than 0")
    private double priceBook;

    private boolean isDelete;
    private TypeProductDto typeProductDto;

    public ProductDto() {
    }

    public ProductDto(int idProduct, String img, String nameProduct, String publicationYear, String author, String describeBook, String nationBook, String publishingCompany, int quantityBooks, double priceBook, boolean isDelete, TypeProductDto typeProductDto) {
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
        this.typeProductDto = typeProductDto;
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

    public TypeProductDto getTypeProductDto() {
        return typeProductDto;
    }

    public void setTypeProductDto(TypeProductDto typeProductDto) {
        this.typeProductDto = typeProductDto;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
