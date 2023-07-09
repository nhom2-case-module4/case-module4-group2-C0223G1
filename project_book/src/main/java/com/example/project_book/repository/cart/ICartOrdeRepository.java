package com.example.project_book.repository.cart;

import com.example.project_book.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICartOrdeRepository extends JpaRepository<CartOrder,Integer> {
    List<CartOrder> findByIdUserIs(int idUser);

    @Query(value ="DELETE FROM cart_order WHERE id_user = :id",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByIdUserIs(@Param("id") int id);

//    @Query(value = "SELECT p.name_product, SUM(od.number_of_detail) AS total_sales FROM products AS p JOIN order_detail AS od ON p.id_product = od.product_id_product JOIN order_book AS ob ON od.order_id_order = ob.id_order WHERE ob.flag_delete = 0 GROUP BY p.name_product ORDER BY total_sales DESC LIMIT 1",nativeQuery = true)

}
