package com.store.sportswear.service.implement;

import com.store.sportswear.entity.Order_Detail;
import com.store.sportswear.repository.OrderDetailRepository;
import com.store.sportswear.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
