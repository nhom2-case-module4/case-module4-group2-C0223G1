package com.example.project_book.service.product;

import com.example.project_book.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> findAllByIsDeleteIsFalse(Pageable pageable);

    Product findById(int id);

    void delete(int id);

    void edit(Product product);

    void add(Product product);


     Page<Product> search(String name, String author,Pageable pageable);
}
