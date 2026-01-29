package com.techno.practice.order.service;

import com.techno.practice.order.client.InventoryClient;
import com.techno.practice.order.dto.OrderRequest;
import com.techno.practice.order.event.OrderPlacedEvent;
import com.techno.practice.order.model.Order;
import com.techno.practice.order.repo.OrderRepository;
//import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
//@RequiredArgsConstructor
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final InventoryClient inventoryyClient;

    @Autowired
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryyClient, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.inventoryyClient = inventoryyClient;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void placeOrder(OrderRequest orderRequest) {
        // Business logic to place an order
        logger.info("Order request from client: {}" , orderRequest);
logger.info("Placing order for SKU: {} with quantity: {}" , orderRequest.skuCode(), orderRequest.quantity());
        var isProductInStock = inventoryyClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
if(isProductInStock) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    order.setSkuCode(orderRequest.skuCode());
    order.setPrice(orderRequest.price());
    order.setQuantity(orderRequest.quantity());

    orderRepository.save(order);
    // send the message to kafka topic
    OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(
            order.getOrderNumber(),
            orderRequest.userDetails().email()
    );
    logger.info("Order placed event sent to Kafka for order number: {}" , order.getOrderNumber());
    kafkaTemplate.send("order-placed", orderPlacedEvent);
    logger.info("Order placed successfully with order number: {}" ,order.getOrderNumber());

}else{
    throw new IllegalArgumentException("Product is not in stock, please try again later.");
}

    }
}
