package com.example.project_book.service.cart;

import com.example.project_book.model.Cart;
import com.example.project_book.model.CartOrder;
import com.example.project_book.model.Order;
import com.example.project_book.model.User;

import java.util.List;

public interface ICartService {
    void oderBook(Cart cart, Order order);

    List<CartOrder> getCartByIdUser(int idUser);

    void deleteCartByIdUser(int idUser);

    void updateCart(CartOrder cartOrder);
}
