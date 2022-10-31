package com.trinhducloc.springboot.controller;

import com.trinhducloc.springboot.dto.OrderRequest;
import com.trinhducloc.springboot.dto.OrderResponse;
import com.trinhducloc.springboot.entity.Order;
import com.trinhducloc.springboot.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/orders", method = RequestMethod.GET)
public class OrderController {
    private OrderService orderService;
//     TO_DO: private PaymentService paymentService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        // create order -> success
//        Order order = orderService.saveOrder();
        //save payment: paymentServiceSavePayment(order)
        // create payment -> ex

        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }
}
