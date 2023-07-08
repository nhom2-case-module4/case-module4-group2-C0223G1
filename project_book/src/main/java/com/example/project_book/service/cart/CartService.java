package com.example.project_book.service.cart;

import com.example.project_book.model.*;
import com.example.project_book.repository.IBooksOrderRepository;
import com.example.project_book.repository.cart.ICartOrdeRepository;
import com.example.project_book.repository.oder_detail.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private IBooksOrderRepository orderRepository;

    @Autowired
    private IOrderDetailRepository oderDetailRepository;

    @Autowired
    ICartOrdeRepository cartOrdeRepository;

    @Override
    public void oderBook(Cart cart, Order order) {
        orderRepository.save(order);
        Order orderNew = orderRepository.findFirstByOrderByIdOrderDesc();
        for (Item i : cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setNumberOfDetail(i.getAmount());
            orderDetail.setProduct(i.getProduct());
            orderDetail.setOrder(orderNew);
            oderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public List<CartOrder> getCartByIdUser(int idUser) {
        return cartOrdeRepository.findByIdUserIs(idUser);
    }

    @Override
    public void deleteCartByIdUser(int idUser) {
        cartOrdeRepository.deleteByIdUserIs(idUser);
    }
}
