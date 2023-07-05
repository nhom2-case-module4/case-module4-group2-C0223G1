package com.example.project_book.service;

import com.example.project_book.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsersService {
//    List<Users>getListUsers();
    void add(Users users);
    Page<Users> findAllByFlagDeleteIsFalse(Pageable pageable);
    Users findById(int id);
    void edit(Users users);
    void delete(Users users);
    Page<Users> findOne(String name,Pageable pageable);
}
