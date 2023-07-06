package com.example.project_book.service.home;

import com.example.project_book.model.Product;
import com.example.project_book.repository.home.IHomeRepesitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeService implements IHomeService {
    @Autowired
    private IHomeRepesitory homeRepesitory;
    @Override
    public List<Product> getlistBook() {
        return homeRepesitory.findByisDeleteIsFalse();
    }

    @Override
    public Product getBookById(int id) {
        return homeRepesitory.findById(id).orElse(null);
    }

    @Override
    public List<Product> getBooksByType(int idType) {
        return homeRepesitory.findByisDeleteIsFalseAndTypeProduct_idProductIs(idType);
    }
}
