package com.example.project_book.repo;

import com.example.project_book.model.RoleUser;
import com.example.project_book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsersRepo extends JpaRepository<User, Integer> {
    @Query(value = "select * from Users AS b where upper(b.name) like concat('%',upper(:name),'%' )", nativeQuery = true)
    Page<User> searchByName(@Param(value = "name") String name, Pageable pageable);

    Page<User> findAllByIsDeleteIsFalse(Pageable pageable);

    User findByEmailUser(String email);

    List<User> findAllByRoleUser(RoleUser roleUser);

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    boolean existsByEmailUser(String emailUser);
}
