package com.project.shopapp.Service;

import com.project.shopapp.DTOS.OrderDetailDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.Order;
import com.project.shopapp.MODELS.OrderDetail;
import com.project.shopapp.MODELS.Product;
import com.project.shopapp.Respository.OrderDetailRespository;
import com.project.shopapp.Respository.OrderRespository;
import com.project.shopapp.Respository.ProductRespository;
import com.project.shopapp.Service.IMP.IMPOrderDetailService;
import com.project.shopapp.Service.IMP.IMPOrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderDetailService implements IMPOrderDetailService {
    private final OrderDetailRespository orderDetailRespository;
    private final OrderRespository orderRespository;
    private final ProductRespository productRespository;

    @SneakyThrows
    @Override
    public OrderDetail CreateOrderDetail(OrderDetailDTO orderDetailDTO) {
        Order order = orderRespository.findById(orderDetailDTO.getOrderId()).
                orElseThrow(()->new DataNotFoundException("Cannot find Order with id " + orderDetailDTO.getOrderId()));
        Product product = productRespository.findById(orderDetailDTO.getProductId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find Product with id " +orderDetailDTO.getProductId()));
        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .color(orderDetailDTO.getColor()).build();
        return orderDetailRespository.save(orderDetail);
    }

    @SneakyThrows
    @Override
    public OrderDetail getOrderDetail(Long id) {
        return orderDetailRespository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Cannot find OrderDetail with id " +id));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetail newOrderDetailData) {
        return null;
    }

    @Override
    public void deleteOrderDetail(Long id) {
        orderDetailRespository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findById(Long orderId) {
        return orderDetailRespository.findOrderDetailById(orderId);
    }
}
