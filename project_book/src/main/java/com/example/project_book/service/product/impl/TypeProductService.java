package com.example.project_book.service.product.impl;

import com.example.project_book.model.TypeProduct;
import com.example.project_book.repository.product.ITypeProductRepository;
import com.example.project_book.service.product.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeProductService implements ITypeProductService {
    @Autowired
    private ITypeProductRepository typeProductRepository;

    @Override
    public List<TypeProduct> getListTypeProduct() {
        return typeProductRepository.findAll();
    }

    @Override
    public void add(TypeProduct typeProduct) {
        typeProductRepository.save(typeProduct);
    }

    @Override
    public TypeProduct getTypeProductById(Integer id) {
        return typeProductRepository.findById(id).get();
    }
}
