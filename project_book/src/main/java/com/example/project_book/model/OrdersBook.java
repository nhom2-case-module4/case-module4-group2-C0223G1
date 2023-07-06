package com.example.project_book.model;

import javax.persistence.*;
import java.time.LocalDate;
@NamedStoredProcedureQuery(
        name = "order_book",
        procedureName = "order_book",
        resultSetMappings = "orderMapping"
)
@SqlResultSetMapping(
        name = "orderMapping",
        classes = @ConstructorResult(
                targetClass = OrdersBook.class,
                columns = {
                        @ColumnResult(name = "id", type = int.class),
                        @ColumnResult(name = "addressPeople"),
                        @ColumnResult(name = "dayOrder", type = LocalDate.class),
                        @ColumnResult(name = "dayTake", type = LocalDate.class),
                        @ColumnResult(name = "flagDelete", type = boolean.class),
                        @ColumnResult(name = "nameProduct"),
                        @ColumnResult(name = "nameStatus"),
                        @ColumnResult(name = "nameUser"),
                        @ColumnResult(name = "noteOrder"),
                        @ColumnResult(name = "numberDetail",type = int.class),

                }
        )
)
@Entity
public class OrdersBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameUser;
    private String nameProduct;
    private String addressPeople;
    private String noteOrder;
    private String nameStatus;
    private LocalDate dayOrder;
    private LocalDate dayTake;
    private int numberDetail;
    private boolean flagDelete;

    public OrdersBook() {
    }

    public OrdersBook(int id, String addressPeople,LocalDate dayOrder,LocalDate dayTake,boolean flagDelete,String nameProduct,String nameStatus,String nameUser,String noteOrder,int numberDetail) {
        this.id = id;
        this.nameUser = nameUser;
        this.nameProduct = nameProduct;
        this.addressPeople = addressPeople;
        this.noteOrder = noteOrder;
        this.nameStatus = nameStatus;
        this.dayOrder = dayOrder;
        this.dayTake = dayTake;
        this.numberDetail = numberDetail;
        this.flagDelete = flagDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getAddressPeople() {
        return addressPeople;
    }

    public void setAddressPeople(String addressPeople) {
        this.addressPeople = addressPeople;
    }

    public String getNoteOrder() {
        return noteOrder;
    }

    public void setNoteOrder(String noteOrder) {
        this.noteOrder = noteOrder;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public LocalDate getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(LocalDate dayOrder) {
        this.dayOrder = dayOrder;
    }

    public LocalDate getDayTake() {
        return dayTake;
    }

    public void setDayTake(LocalDate dayTake) {
        this.dayTake = dayTake;
    }

    public int getNumberDetail() {
        return numberDetail;
    }

    public void setNumberDetail(int numberDetail) {
        this.numberDetail = numberDetail;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
