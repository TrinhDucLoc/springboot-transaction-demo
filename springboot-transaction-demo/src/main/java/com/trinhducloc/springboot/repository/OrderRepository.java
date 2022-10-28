package com.trinhducloc.springboot.repository;

import com.trinhducloc.springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
