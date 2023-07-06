package com.example.project_book.repo;

import com.example.project_book.model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersTypeRepo extends JpaRepository<RoleUser,Integer> {

}
