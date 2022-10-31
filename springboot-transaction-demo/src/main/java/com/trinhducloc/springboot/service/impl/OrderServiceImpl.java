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
//    service link repository: orderRepository, paymentRepository
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    //    Vi sao cho nay dung @Transactional

    @Transactional
    //    Truyen vao mot OrderRequest de dat hang
    public OrderResponse placeOrder(OrderRequest orderRequest){
//        Khoi tao doi tuong order tu OrderRequest.getOrder()
        Order order = orderRequest.getOrder();
//        Trang thai: IN PROGRESS
        order.setStatus("IN PROGRESS");
//       Cai dat so luong theo doi don hang: UUID de tao ID voi do dai 128bit
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
//        luu entity "order" vao orderRepository
        orderRepository.save(order);

//        khoi tao doi tuong payment tu orderRequest.getPayment
        Payment payment = orderRequest.getPayment();

//        kiem tra xem loai the co dung la "DEBIT" khong
//        => Neu sai tra ve PaymentException
        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

//      cai dat "setOrderId" voi OrderId tuong ung
        payment.setOrderId(order.getId());
//        luu entity "payment" vao paymentRepository
        paymentRepository.save(payment);

//        khoi tao orderResponse
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
