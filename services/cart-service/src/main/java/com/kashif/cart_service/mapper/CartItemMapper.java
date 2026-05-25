package com.kashif.cart_service.mapper;

import com.kashif.cart_service.dto.CartItemResponse;
import com.kashif.cart_service.dto.ProductResponse;
import com.kashif.cart_service.model.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemMapper {

    public CartItem productToCartItem(ProductResponse product, int quantity){
        return CartItem.builder()
                .productId(product.getId())
                .imageUrl(product.getImageUrl())
                .quantity(quantity)
                .price(product.getPrice())
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .build();
    }

    public CartItemResponse cartItemToCartItemResponse(CartItem item){
        return CartItemResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .imageUrl(item.getImageUrl())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .totalPrice(item.getTotalPrice())
                .build();
    }

    public List<CartItemResponse> cartItemListToCartItemResponseList(List<CartItem> items){
        List<CartItemResponse> responseList = new ArrayList<>();
        for (CartItem item : items){
            responseList.add(
                    CartItemResponse.builder()
                    .id(item.getId())
                    .productId(item.getProductId())
                    .imageUrl(item.getImageUrl())
                    .quantity(item.getQuantity())
                    .price(item.getPrice())
                    .totalPrice(item.getTotalPrice())
                    .build()
            );
        }
        return responseList;
    }
}
