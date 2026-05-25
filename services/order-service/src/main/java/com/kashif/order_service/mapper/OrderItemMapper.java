package com.kashif.order_service.mapper;

import com.kashif.order_service.dto.CartItemResponse;
import com.kashif.order_service.dto.OrderItemResponse;
import com.kashif.order_service.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {

    public List<OrderItem> cartItemListResponseToOrderItemList(List<CartItemResponse> responses){
        List<OrderItem> items = new ArrayList<>();
        for(CartItemResponse response : responses){
            items.add(
                    OrderItem.builder()
                            .productId(response.getProductId())
                            .imageUrl(response.getImageUrl())
                            .quantity(response.getQuantity())
                            .price(response.getPrice())
                            .build()
            );
        }
        return items;
    }

    public List<OrderItemResponse> orderItemListToOrderItemResponseList(List<OrderItem> items){
        List<OrderItemResponse> responseList = new ArrayList<>();
        for (OrderItem item: items){
            responseList.add(
                    OrderItemResponse.builder()
                            .id(item.getId())
                            .productId(item.getProductId())
                            .imageUrl(item.getImageUrl())
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .build()
            );
        }
        return responseList;
    }
}
