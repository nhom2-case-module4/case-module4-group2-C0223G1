package com.example.project_book.service.order;

import com.example.project_book.model.Order;
import com.example.project_book.projections.OrderDetailProjection;
import com.example.project_book.projections.OrderProjection;
import com.example.project_book.repository.IBooksOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IBooksOrderRepository booksOrderRepository;
    @Override
    public Page<OrderProjection> findAllOrder(Pageable pageable) {
        return booksOrderRepository.findBooksOrder(pageable);
    }

    @Override
    public Optional<Order> findByOrder(int id) {
        return booksOrderRepository.findById(id);
    }

    @Override
    public void deleteOrder(Order order) {
        booksOrderRepository.save(order);
    }

    @Override
    public void giveProduct(int number, int id) {
        booksOrderRepository.giveProduct(number,id);
    }

    @Override
    public void returnProduct(int number, int id) {
        booksOrderRepository.returnProduct(number,id);
    }

    @Override
    public void optionStatus(int option, int id) {
        booksOrderRepository.updateStatus(option,id);
    }

    @Override
    public void updateOrder(Order order) {
        booksOrderRepository.save(order);
    }

    @Override
    public Page<OrderProjection> searchAllOrder(String dateStart, String dateEnd, int id, Pageable pageable) {
        return booksOrderRepository.searchBooksOrders(dateStart,dateEnd,id,pageable);
    }

    @Override
    public Page<OrderDetailProjection> findDetail(int id, Pageable pageable) {
        return booksOrderRepository.findAllOrderDetail(id,pageable);
    }

}
