package com.store.sportswear.repository;

import com.store.sportswear.entity.Order_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_Detail,Integer> {
}
