//package com.example.project_book.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "order_detail")
//public class OrderDetail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int idDetail;
//    private int numberOfDetail;
//    @ManyToOne
//    private Product product;
//    @ManyToOne
//    private Order order;
//
//    public OrderDetail(int idDetail, int numberOfDetail, Product product, Order order) {
//        this.idDetail = idDetail;
//        this.numberOfDetail = numberOfDetail;
//        this.product = product;
//        this.order = order;
//    }
//
//    public OrderDetail() {
//    }
//
//    public int getIdDetail() {
//        return idDetail;
//    }
//
//    public void setIdDetail(int idDetail) {
//        this.idDetail = idDetail;
//    }
//
//    public int getNumberOfDetail() {
//        return numberOfDetail;
//    }
//
//    public void setNumberOfDetail(int numberOfDetail) {
//        this.numberOfDetail = numberOfDetail;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//}
