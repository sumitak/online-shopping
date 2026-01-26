package com.techno.practice.inventory_service.service;

import com.techno.practice.inventory_service.repository.InventoryRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/*
*RequiredArgsConstructor is required to create a constructor with required arguments
 */
public class InventoryService {

    @Autowired
    private final InventoryRespository inventoryRespository;

    public InventoryService(InventoryRespository inventoryRespository) {
        this.inventoryRespository = inventoryRespository;
    }

    public boolean isInStock(String skuCode, int quantity) {

        //find an inventory by sku code and check if the quantity > = 0
        return inventoryRespository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
