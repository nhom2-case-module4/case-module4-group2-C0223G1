package com.example.project_book.repository.product;

import com.example.project_book.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByIsDeleteIsFalse(Pageable pageable);

    Product getProductByIdProductAndIsDeleteIsFalse(int id);

    Page<Product> findAllByNameProductContainingIgnoreCase(String name,Pageable pageable);
}
