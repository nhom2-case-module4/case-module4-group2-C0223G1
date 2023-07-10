package com.example.project_book.service.product;

import com.example.project_book.model.TypeProduct;
import com.example.project_book.repository.product.ITypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITypeProductService {
    List<TypeProduct> getListTypeProduct();

    void add(TypeProduct typeProduct);

    TypeProduct getTypeProductById(Integer id);

    void delete(int id);
}
