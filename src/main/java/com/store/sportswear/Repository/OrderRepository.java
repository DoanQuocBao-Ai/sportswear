package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Order;
import com.store.sportswear.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>, QuerydslPredicateExecutor<Order> {
    public List<Order> findByStatusAndShipper(String status, User shipper);
    @Query(value="select DATE_FORMAT(or.receive_at,'%m') as month," +
            "DATE_FORMAT(or.receive_at,'%Y') as year," +
            "sum(detail.number*detail.price) as total from order or, order_detail detail " +
            "where or.id=detail.product_id and or.status='Hoàn thành' " +
            "group by DATE_FORMAT(or.receive_at,'%Y%m') order by year asc")
    public List<Object> findByDatetime();
    public List<Order> findByUser(User user);
    public int countStatusOrder(String status);

}
