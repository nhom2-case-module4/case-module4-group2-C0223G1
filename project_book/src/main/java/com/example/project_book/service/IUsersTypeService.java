package com.example.project_book.service;


import com.example.project_book.model.UsersType;

import java.util.List;

public interface IUsersTypeService {
    List<UsersType> getListUsers();
    void add(UsersType usersType);
}
