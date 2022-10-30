package com.trinhducloc.springboot.service.impl;

import com.trinhducloc.springboot.dto.OrderRequest;
import com.trinhducloc.springboot.dto.OrderResponse;
import com.trinhducloc.springboot.entity.Order;
import com.trinhducloc.springboot.entity.Payment;
import com.trinhducloc.springboot.exception.PaymentException;
import com.trinhducloc.springboot.repository.OrderRepository;
import com.trinhducloc.springboot.repository.PaymentRepository;
import com.trinhducloc.springboot.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest){
        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;
    }

//    Check randomUUID().toString() with debug
//    public static void main(String[] args){
//        String a = UUID.randomUUID().toString();
//    }
}
