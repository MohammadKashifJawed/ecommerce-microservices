package com.kashif.cart_service.mapper;

import com.kashif.cart_service.dto.CartResponse;
import com.kashif.cart_service.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper itemMapper;

    public CartResponse cartToCartResponse(Cart cart){
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(itemMapper.cartItemListToCartItemResponseList(cart.getItems()))
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}
