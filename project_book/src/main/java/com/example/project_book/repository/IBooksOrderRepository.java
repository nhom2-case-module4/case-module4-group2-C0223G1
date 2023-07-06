package com.example.project_book.repository;

import com.example.project_book.model.BooksOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBooksOrderRepository extends JpaRepository<BooksOrder,Integer> {
}
