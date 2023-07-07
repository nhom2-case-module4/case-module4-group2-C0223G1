package com.example.project_book.service.cart;

import com.example.project_book.model.Cart;
import com.example.project_book.model.Item;
import com.example.project_book.model.Order;
import com.example.project_book.model.OrderDetail;
import com.example.project_book.repository.IBooksOrderRepository;
import com.example.project_book.repository.oder_detail.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    @Autowired
    private IBooksOrderRepository orderRepository;

    @Autowired
    private IOrderDetailRepository oderDetailRepository;
    @Override
    public void oderBook(Cart cart, Order order) {
        orderRepository.save(order);
        Order orderNew = orderRepository.findFirstByOrderByIdOrderDesc();
        for (Item i: cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setNumberOfDetail(i.getAmount());
            orderDetail.setProduct(i.getProduct());
            orderDetail.setOrder(orderNew);
            oderDetailRepository.save(orderDetail);
        }
    }
}
