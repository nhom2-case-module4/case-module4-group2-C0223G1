package com.example.project_book.repository;

import com.example.project_book.model.OrdersBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface IOrderRepository extends JpaRepository<OrdersBook, Integer> {
    @Transactional(readOnly = true)
    @Procedure(name="order_book")
    Page<OrdersBook> getOrderBook(Pageable pageable);
}
