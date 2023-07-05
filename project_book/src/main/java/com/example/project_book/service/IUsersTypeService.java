package com.example.bookstore.service;

import com.example.bookstore.model.Users;
import com.example.bookstore.model.UsersType;

import java.util.List;

public interface IUsersTypeService {
    List<UsersType> getListUsers();
    void add(UsersType usersType);
}
