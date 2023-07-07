package com.example.project_book.service.cart;

import com.example.project_book.model.Cart;
import com.example.project_book.model.Order;
import com.example.project_book.model.User;

public interface ICartService {
    void oderBook(Cart cart, Order order);
}
