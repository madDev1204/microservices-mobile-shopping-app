package com.example.service;

import com.example.dto.OrderLineItemsDto;
import com.example.dto.OrderRequest;
import com.example.entity.Order;
import com.example.entity.OrderLineItems;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order(); //order object has 3 attributes
        //order number
        order.setOrderNumber(UUID.randomUUID().toString());
        //mapping object of orderLineItemsDto to orderLineItems
        List<OrderLineItems> orderLineItems =
                orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                        .toList();

        order.setOrderLineItemsList(orderLineItems); //order created from order request
        //before saving order to db CHECK INVENTORY - SYNC COMMUNICATION EXAMPLE
        //calling Inventory service
        //placing order only if product in stock

        //save order to db
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
