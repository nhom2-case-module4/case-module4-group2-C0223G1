package com.example.project_book.service.order;

import com.example.project_book.model.Order;
import com.example.project_book.projections.OrderDetailProjection;
import com.example.project_book.projections.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface IOrderService {
    Page<OrderProjection> findAllOrder(Pageable pageable);
    Optional<Order> findByOrder(int id);
    void deleteOrder(Order order);
    void giveProduct(int number,int id);
    void returnProduct(int number,int id);
    void optionStatus(int option,int id);
    void updateOrder(Order order);
    Page<OrderProjection> searchAllOrder(String dateStart,String dateEnd,int id, Pageable pageable);
    Page<OrderDetailProjection> findDetail(int id,Pageable pageable);
}
