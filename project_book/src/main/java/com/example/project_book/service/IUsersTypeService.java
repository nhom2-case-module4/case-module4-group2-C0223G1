package com.example.project_book.service;


import com.example.project_book.model.RoleUser;

import java.util.List;

public interface IUsersTypeService {
    List<RoleUser> getListUsers();
    void add(RoleUser usersType);
}
