package com.store.sportswear.service;

import com.store.sportswear.dto.SearchOrderDto;
import com.store.sportswear.entity.Order;
import com.store.sportswear.entity.UserSystem;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface IOrderService {
    List<Order> getOrderByFilter(SearchOrderDto object, int page) throws ParseException;
    Order getOrderById(int id);
    Order updateOrderId(Order order);
    Page<Order> getOrderByShipper(SearchOrderDto object, int page, int size, UserSystem shipper) throws ParseException;
    Order saveOrder(Order order);
    List<Object> getOrderByMonthYear();
    List<Order> getOrderByStatusAndShipper(String status, UserSystem shipper);
    List<Order> getOrderByUser(UserSystem userSystem);
    int countOrderByStatus(String status);
}
