package com.example.service;

import com.example.dto.InventoryResponse;
import com.example.entity.Inventory;
import com.example.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())  //incomplete does not validate if skuCode is in inventory or not changes needed HERE
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
