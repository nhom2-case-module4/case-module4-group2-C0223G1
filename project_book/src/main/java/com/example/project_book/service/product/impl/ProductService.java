package com.example.project_book.service.product.impl;

import com.example.project_book.model.Product;
import com.example.project_book.model.User;
import com.example.project_book.repository.product.IProductRepository;
import com.example.project_book.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<Product> findAllByIsDeleteIsFalse(Pageable pageable) {
        return productRepository.findAllByIsDeleteIsFalse(pageable);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();

    }

    @Override
    public void delete(int id) {
        Product product = productRepository.getProductByIdProductAndIsDeleteIsFalse(id);
        product.setDelete(true);
        productRepository.save(product);
    }

    @Override
    public void edit(Product product) {
        productRepository.save(product);
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public Page<Product> findOne(String name, Pageable pageable) {
        return this.productRepository.findAllByNameProductContainingIgnoreCase(name,pageable);
    }
}
