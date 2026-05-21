package com.kashif.cart_service.service;

import com.kashif.cart_service.dto.CartRequest;
import com.kashif.cart_service.dto.CartResponse;
import com.kashif.cart_service.dto.ProductResponse;
import com.kashif.cart_service.exception.CartNotFoundException;
import com.kashif.cart_service.exception.ProductOutOfStockException;
import com.kashif.cart_service.feign.ProductFeign;
import com.kashif.cart_service.mapper.CartItemMapper;
import com.kashif.cart_service.mapper.CartMapper;
import com.kashif.cart_service.model.Cart;
import com.kashif.cart_service.model.CartItem;
import com.kashif.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductFeign productFeign;
    private final CartItemMapper mapper;
    private final CartMapper cartMapper;
    private final CartRepository repository;

    public CartResponse addToCart(CartRequest request) {
        ProductResponse product = Objects.requireNonNull(productFeign.getProduct(
                request.getProductId().toString()).getBody()).getData();
        if (request.getQuantity() > product.getStock()){
            throw new ProductOutOfStockException("Product with given quantity not available");
        }
        CartItem cartItem = mapper.productToCartItem(product, request.getQuantity());

        Cart existingCart = repository.findByUserId(request.getUserId());
        if (existingCart != null){  // If cart already exist
            List<CartItem> items = existingCart.getItems();
            CartItem existingItem = items.stream().filter( // if item exist in existing cart
                    (item) -> {
                        if (item.getId().equals(cartItem.getId())){
                            if (item.getQuantity() + request.getQuantity() > product.getStock()){
                                throw new ProductOutOfStockException
                                        ("Product with given quantity not available");
                            }
                            item.setQuantity(item.getQuantity() + request.getQuantity());
                            item.setPrice(item.getPrice()
                                    .multiply(BigDecimal.valueOf(request.getQuantity())));
                            return true;
                        }
                        return false;
                    }
            ).findFirst().orElse(null);
            if (existingItem == null){ // if item doesn't exist in existing cart
                items.add(cartItem);
            }
            existingCart.setTotalPrice(totalCartPrice(items));
            repository.save(existingCart);
            return cartMapper.cartToCartResponse(existingCart);
        }
        else{ // If cart not exist for given userId
            Cart cart = new Cart();
            cart.setUserId(request.getUserId());
            List<CartItem> cartItemList = cart.getItems();
            cartItemList.add(cartItem);
            cart.setItems(cartItemList);
            cart.setTotalPrice(totalCartPrice(cartItemList));
            repository.save(cart);
            return cartMapper.cartToCartResponse(cart);
        }
    }

    public BigDecimal totalCartPrice(List<CartItem> items){
        return items.stream().reduce(
                BigDecimal.ZERO,
                (sum, item) -> sum.add(item.getPrice()),
                BigDecimal::add
        );
    }

    public CartResponse updateCart(CartRequest request, long cartId) {
        Cart cart = repository.findById(cartId).orElseThrow(
                () -> new CartNotFoundException("Cart with given id not found")
        );
        List<CartItem> items = cart.getItems().stream().peek(item -> {
            if(item.getId().equals(request.getProductId())){
                item.setQuantity(request.getQuantity());
                item.setPrice(item.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            }
        }).collect(Collectors.toCollection(ArrayList::new));
        cart.setTotalPrice(totalCartPrice(items));
        cart.getItems().clear();
        cart.setItems(items);
        return cartMapper.cartToCartResponse(repository.save(cart));
    }


    public CartResponse getCart(long cartId) {
        return cartMapper.cartToCartResponse(
                repository.findById(cartId).orElseThrow(
                        () -> new CartNotFoundException("Cart with given id not found")
                )
        );
    }

    public void deleteCart(long cartId) {
        repository.delete(
                repository.findById(cartId).orElseThrow(
                        () -> new CartNotFoundException("Cart with given id not found")
                )
        );
    }
}
