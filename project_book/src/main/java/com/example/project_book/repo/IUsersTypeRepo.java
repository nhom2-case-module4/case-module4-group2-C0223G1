package com.example.bookstore.repo;

import com.example.bookstore.model.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersTypeRepo extends JpaRepository<UsersType,Integer> {

}
