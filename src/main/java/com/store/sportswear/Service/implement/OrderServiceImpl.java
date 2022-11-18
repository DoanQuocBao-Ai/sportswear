package com.store.sportswear.Service.implement;

import com.querydsl.core.BooleanBuilder;
import com.store.sportswear.Dto.SearchOrderDto;
import com.store.sportswear.Entity.Order;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Repository.OrderRepository;
import com.store.sportswear.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderRepository repository;

    public OrderServiceImpl() {
        super();
    }

    @Override
    public List<Order> getOrderByFilter(SearchOrderDto object, int page) throws ParseException {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String status=object.getStatus();
        String fromDate=object.getFromDate();
        String toDate=object.getToDate();
        SimpleDateFormat formatDate= new SimpleDateFormat("dd-mm-yy");

        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Order updateOrderId(Order order) {
        return repository.save(order);
    }

    @Override
    public Page<Order> getOrderByShipper(SearchOrderDto object, int page, int size, UserSystem shipper) throws ParseException {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Object> getOrderByMonthYear() {
        return repository.findByDatetime();
    }

    @Override
    public List<Order> getOrderByStatusAndShipper(String status, UserSystem shipper) {
        return repository.findByStatusAndShipper(status,shipper);
    }

    @Override
    public List<Order> getOrderByUser(UserSystem userSystem) {
        return repository.findByUser(userSystem);
    }

    @Override
    public int countOrderByStatus(String status) {
        return repository.countStatusOrder(status);
    }
}
