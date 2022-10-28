package com.trinhducloc.springboot.service;

import com.trinhducloc.springboot.dto.OrderRequest;
import com.trinhducloc.springboot.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
