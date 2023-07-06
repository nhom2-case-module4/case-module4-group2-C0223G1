package com.example.project_book.projections;

import java.time.LocalDate;

public interface OrderProjection {
    int getId();
    String getAddressPeople();
    LocalDate getDayOrder();
    LocalDate getDayTake();
    boolean getFlagDelete();
    String getNameProduct();
//    String get
}
