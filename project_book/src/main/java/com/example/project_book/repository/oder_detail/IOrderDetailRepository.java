package com.example.project_book.repository.oder_detail;

import com.example.project_book.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
