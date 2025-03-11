package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.OrderDetailDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.OrderDetail;

import java.util.List;

public interface IMPOrderDetailService {
    OrderDetail CreateOrderDetail(OrderDetailDTO orderDetail);

    OrderDetail getOrderDetail(Long id);


    OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData) throws DataNotFoundException;

    void deleteOrderDetail(Long id);

    List<OrderDetail> findById(Long orderId);
}
