package com.store.sportswear.service;

import com.store.sportswear.entity.Order_Detail;

import java.util.List;

public interface IOrderDetailService {
    List<Order_Detail> saveOrderDetail(List<Order_Detail> list);
}
