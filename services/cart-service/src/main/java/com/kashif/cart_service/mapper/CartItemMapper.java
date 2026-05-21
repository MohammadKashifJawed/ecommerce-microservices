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
                .quantity(quantity)
                .price(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .build();
    }

    public CartItemResponse cartItemToCartItemResponse(CartItem item){
        return CartItemResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }

    public List<CartItemResponse> cartItemListToCartItemResponseList(List<CartItem> items){
        List<CartItemResponse> responseList = new ArrayList<>();
        for (CartItem item : items){
            responseList.add(
                    CartItemResponse.builder()
                    .id(item.getId())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .price(item.getPrice())
                    .build()
            );
        }
        return responseList;
    }
}
