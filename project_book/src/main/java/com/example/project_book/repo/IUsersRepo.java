package com.example.project_book.repo;

import com.example.project_book.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUsersRepo extends JpaRepository<Users,Integer> {
    @Query(value = "select * from Users AS b where upper(b.name) like concat('%',upper(:name),'%' )", nativeQuery = true)
    Page<Users> searchByName(@Param(value = "name") String name, Pageable pageable);
    Page<Users>findAllByFlagDeleteIsFalse(Pageable pageable);
}
