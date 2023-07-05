package com.example.bookstore.service;

import com.example.bookstore.model.UsersType;
import com.example.bookstore.repo.IUsersRepo;
import com.example.bookstore.repo.IUsersTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService implements IUsersTypeService {
    @Autowired
    private IUsersTypeRepo usersTypeRepo;

    @Override
    public List<UsersType> getListUsers() {
        return usersTypeRepo.findAll();
    }

    @Override
    public void add(UsersType usersType) {
            usersTypeRepo.save(usersType);
    }
}
