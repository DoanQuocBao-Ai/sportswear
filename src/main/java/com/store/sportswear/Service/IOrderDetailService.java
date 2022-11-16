package com.store.sportswear.Service;

import com.store.sportswear.Entity.Order_Detail;

import java.util.List;

public interface IOrderDetailService {
    List<Order_Detail> saveOrderDetail(List<Order_Detail> list);
}
