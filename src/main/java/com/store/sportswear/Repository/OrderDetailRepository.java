package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Order_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<Order_Detail,Long> {
}
