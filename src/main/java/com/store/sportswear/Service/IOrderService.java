package com.store.sportswear.Service;

import com.store.sportswear.Dto.SearchOrderDto;
import com.store.sportswear.Entity.Order;
import com.store.sportswear.Entity.UserSystem;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface IOrderService {
    List<Order> getOrderByFilter(SearchOrderDto object, int page) throws ParseException;
    Order getOrderById(Long id);
    Order updateOrderId(Order order);
    Page<Order> getOrderByShipper(SearchOrderDto object, int page, int size, UserSystem shipper) throws ParseException;
    Order saveOrder(Order order);
    List<Object> getOrderByMonthYear();
    List<Order> getOrderByStatusAndShipper(String status, UserSystem shipper);
    List<Order> getOrderByUser(UserSystem userSystem);
    int countOrderByStatus(String status);
}
