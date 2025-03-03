package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.OrderDTO;
import com.project.shopapp.Respones.OrderResponse;

import java.util.List;

public interface IMPOrderService {
    OrderResponse createOrder(OrderDTO order);
    OrderResponse getOrder(Long id);
    OrderResponse updateOrder(Long id, OrderDTO order);
    void deleteOrder(Long id);
    List<OrderResponse> getAllOrders(Long userId);
}
