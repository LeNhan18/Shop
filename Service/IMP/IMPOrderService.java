package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.OrderDTO;
import com.project.shopapp.MODELS.Order;

import java.util.List;

public interface IMPOrderService {
    Order createOrder(OrderDTO order);
    Order getOrder(Long id);
    Order updateOrder(Long id, OrderDTO order);
    void deleteOrder(Long id);
    List<Order> getAllOrders(Long userId);
}
