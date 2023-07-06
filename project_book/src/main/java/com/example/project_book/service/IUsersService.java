package com.example.project_book.service;

import com.example.project_book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsersService {
//    List<Users>getListUsers();
    void add(User users);
    Page<User> findAllByIsDeleteIsFalse(Pageable pageable);
    User findById(int id);
    void edit(User users);
    void delete(User users);
    Page<User> findOne(String name, Pageable pageable);
    void deleteById(int id);

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    boolean existsByEmailUser(String emailUser);
}
