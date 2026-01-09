package com.techno.practice.inventory_service.service;

import com.techno.practice.inventory_service.repository.InventoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
*RequiredArgsConstructor is required to create a constructor with required arguments
 */
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRespository inventoryRespository;

    public boolean isInStock(String skuCode, int quantity) {

        //find an inventory by sku code and check if the quantity > = 0
        return inventoryRespository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
