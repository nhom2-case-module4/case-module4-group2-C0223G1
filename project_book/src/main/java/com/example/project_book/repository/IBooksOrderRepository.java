package com.example.project_book.repository;

import com.example.project_book.model.Order;
import com.example.project_book.projections.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface IBooksOrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = " SELECT  id_order as id,address_people as addressPeople,day_order as dayOrder,day_take as dayTake,flag_delete as flagDelete ,name_product as nameProduct,name_status as nameStatus,`name` as nameUser,note_order as noteOrder,number_of_detail as numberDetail from order_detail od\n" +
            "    join order_book ob on od.order_id_order=ob.id_order\n" +
            "    join status_order so on so.id_status=ob.status_id_status\n" +
            "    join products p on p.id_product = od.product_id_product\n" +
            "    join `users` u on u.id_user = ob.user_id_user where ob.flag_delete=0",nativeQuery = true)
    Page<OrderProjection> findBooksOrder(Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "UPDATE `books_shop`.`order_book` SET `status_id_status` = :option WHERE (`id_order` = :id)",nativeQuery = true)
    void updateStatus(@Param("id") int id,@Param("option")int option);

    Order findFirstByOrderByIdOrderDesc();
    @Query(value = " SELECT  id_order as id,address_people as addressPeople,day_order as dayOrder,day_take as dayTake,flag_delete as flagDelete ,name_product as nameProduct,name_status as nameStatus,`name` as nameUser,note_order as noteOrder,number_of_detail as numberDetail from order_detail od\n" +
            "    join order_book ob on od.order_id_order=ob.id_order\n" +
            "    join status_order so on so.id_status=ob.status_id_status\n" +
            "    join products p on p.id_product = od.product_id_product\n" +
            "    join `users` u on u.id_user = ob.user_id_user where ob.flag_delete=0 AND (day_order BETWEEN :dateStart AND :dateEnd) AND id_status=:id ",nativeQuery = true)
    Page<OrderProjection> searchBooksOrders(@Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd,@Param("id")int id, Pageable pageable);
}
