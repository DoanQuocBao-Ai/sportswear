package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Order_Detail;
import com.store.sportswear.Repository.OrderDetailRepository;
import com.store.sportswear.Service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository repository;

    public OrderDetailServiceImpl() {
        super();
    }

    @Override
    public List<Order_Detail> saveOrderDetail(List<Order_Detail> list) {
        return repository.saveAll(list);
    }
}
