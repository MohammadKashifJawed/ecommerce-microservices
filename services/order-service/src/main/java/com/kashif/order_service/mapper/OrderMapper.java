package com.kashif.order_service.mapper;

import com.kashif.order_service.dto.CartResponse;
import com.kashif.order_service.dto.OrderRequest;
import com.kashif.order_service.dto.OrderResponse;
import com.kashif.order_service.model.Order;
import com.kashif.order_service.model.OrderStatus;
import com.kashif.order_service.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public Order orderRequestToOrder(CartResponse cartResponse, String paymentStatus, Long addressId){
        return Order.builder()
                .userId(cartResponse.getUserId())
                .totalAmount(cartResponse.getTotalPrice())
                .status(OrderStatus.CONFIRMED)
                .paymentStatus(PaymentStatus.valueOf(paymentStatus.toUpperCase()))
                .addressId(addressId)
                .items(orderItemMapper.cartItemListResponseToOrderItemList(cartResponse.getItems()))
                .build();
    }

    public OrderResponse orderToOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getStatus().toString())
                .paymentStatus(order.getPaymentStatus().toString())
                .addressId(order.getAddressId())
                .createdAt(order.getCreatedAt())
                .items(orderItemMapper.orderItemListToOrderItemResponseList(order.getItems()))
                .build();

    }

    public List<OrderResponse> orderListToOrderResponseList(List<Order> order){
        return null;
    }
}
