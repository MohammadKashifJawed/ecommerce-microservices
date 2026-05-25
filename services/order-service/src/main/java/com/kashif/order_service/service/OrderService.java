package com.kashif.order_service.service;

import com.kashif.order_service.dto.*;
import com.kashif.order_service.exception.OrderNotFoundException;
import com.kashif.order_service.feign.CartFeignClient;
import com.kashif.order_service.feign.UserFeignClient;
import com.kashif.order_service.mapper.OrderMapper;
import com.kashif.order_service.model.Order;
import com.kashif.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CartFeignClient cartFeign;
    private final OrderMapper orderMapper;
    private final UserFeignClient userFeign;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        CartResponse cartResponse = Objects.requireNonNull(cartFeign.getCartById(request.getUserId()).getBody()).getData();
        AddressResponse address = Objects.requireNonNull(userFeign.getAddress(request.getAddressId(), request.getUserId()).getBody()).getData();
        Order order = orderMapper.orderRequestToOrder(
                cartResponse,
                request.getPaymentStatus(),
                address.getId()
        );
        cartFeign.deleteCart(request.getUserId());
        return orderMapper.orderToOrderResponse(
                repository.save(order)
        );
    }


    public void deleteOrder(long userId, long orderId) {
        repository.delete(
                repository.findByUserId(userId).orElseThrow(
                        () -> new OrderNotFoundException("No order exist for user with given id")
                )
        );
    }
}
