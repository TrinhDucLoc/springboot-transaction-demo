package com.trinhducloc.springboot.dto;

import com.trinhducloc.springboot.entity.Order;
import com.trinhducloc.springboot.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
