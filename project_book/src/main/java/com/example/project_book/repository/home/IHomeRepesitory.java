package com.example.project_book.repository.home;

import com.example.project_book.model.Product;
import com.example.project_book.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHomeRepesitory extends JpaRepository<Product,Integer> {
    List<Product> findByisDeleteIsFalse();

    List<Product> findByisDeleteIsFalseAndTypeProduct_idProductIs(int idType);

        @Query(value = "SELECT p.name_product as nameProduct, SUM(od.number_of_detail) AS totalSell FROM products AS p JOIN order_detail AS od ON p.id_product = od.product_id_product JOIN order_book AS ob ON od.order_id_order = ob.id_order WHERE ob.flag_delete = 0 GROUP BY p.name_product ORDER BY totalSell DESC LIMIT 1",nativeQuery = true)
    ProductProjection getProductBestSellByName();

    Product findBynameProductIs(String name);

    List<Product> findByNameProductContainingIgnoreCase(String name);
}
