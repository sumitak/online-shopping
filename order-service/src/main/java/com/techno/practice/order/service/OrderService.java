package com.techno.practice.order.service;

import com.techno.practice.order.client.InventoryClient;
import com.techno.practice.order.dto.OrderRequest;
import com.techno.practice.order.model.Order;
import com.techno.practice.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryyClient;


    public void placeOrder(OrderRequest orderRequest) {
        // Business logic to place an order

        var isProductInStock = inventoryyClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
if(isProductInStock) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    order.setSkuCode(orderRequest.skuCode());
    order.setPrice(orderRequest.price());
    order.setQuantity(orderRequest.quantity());

    orderRepository.save(order);
}else{
    throw new IllegalArgumentException("Product is not in stock, please try again later.");
}

    }
}
