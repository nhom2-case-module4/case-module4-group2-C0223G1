package com.example.project_book.repository.cart;

import com.example.project_book.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartOrdeRepository extends JpaRepository<CartOrder,Integer> {
    List<CartOrder> findByIdUserIs(int idUser);

    void deleteByIdUserIs(int idUser);
}
