package com.example.project_book.service;

import com.example.project_book.model.OrdersBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<OrdersBook> getOrders(Pageable pageable);
}
