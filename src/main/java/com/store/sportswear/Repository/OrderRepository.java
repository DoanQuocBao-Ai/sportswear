package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Order;
import com.store.sportswear.Entity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long>, QuerydslPredicateExecutor<Order> {
    List<Order> findByStatusAndShipper(String status, UserSystem shipper);
    @Query(value="select DATE_FORMAT(or.receive_at,'%m') as month," +
            "DATE_FORMAT(or.receive_at,'%Y') as year," +
            "sum(detail.number*detail.price) as total from order or, order_detail detail " +
            "where or.id=detail.product_id and or.status='Hoàn thành' " +
            "group by DATE_FORMAT(or.receive_at,'%Y%m') order by year asc")
    List<Object> findByDatetime();
    List<Order> findByUser(UserSystem userSystem);
    int countStatusOrder(String status);

}
