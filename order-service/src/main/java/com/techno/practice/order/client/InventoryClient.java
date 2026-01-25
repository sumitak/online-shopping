package com.techno.practice.order.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory/isInStock")
    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback")
    @Retry(name="inventory")
    boolean isInStock(@RequestParam String skuCode,@RequestParam int quantity);

    default boolean inventoryFallback(String skuCode, int quantity, Throwable throwable) {
        log.error("Inventory service is down. Fallback method invoked for skuCode: {}", skuCode);
        log.error("Failure reason: ", throwable.getMessage());
        return false; // Assume not in stock when the service is down
    }
}
