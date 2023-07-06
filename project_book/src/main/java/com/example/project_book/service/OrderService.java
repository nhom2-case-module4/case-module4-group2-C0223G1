package com.example.project_book.service;

import com.example.project_book.model.OrdersBook;
import com.example.project_book.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;
    @Override
    public Page<OrdersBook> getOrders(Pageable pageable) {
        return orderRepository.getOrderBook(pageable);
    }
}
