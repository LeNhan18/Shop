package com.project.shopapp.Service;

import com.project.shopapp.DTOS.OrderDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.Order;
import com.project.shopapp.MODELS.OrderStatus;
import com.project.shopapp.MODELS.User;
import com.project.shopapp.Respones.OrderResponse;
import com.project.shopapp.Respository.OrderRespository;
import com.project.shopapp.Respository.UserRespository;
import com.project.shopapp.Service.IMP.IMPOrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IMPOrderService{
    private final UserRespository userRespository;
    private final OrderRespository orderRespository;
    private static  ModelMapper modelMapper;
    @SneakyThrows
    @Override
    public OrderResponse createOrder(OrderDTO order) {
        User user = userRespository.findById(order.getUserId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find order by id " + order.getUserId()));
        //convert orderDTO ->Order
        //Dung thu vien ModelMapper
        //Tao mot luong bang anh xa de kiem soat anh xa
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper ->mapper.skip(Order :: setId));
        //Cap nhat cac truong cua don hang tu OrderDTO
        Order order1 = new Order();
        modelMapper.map(order, order1);
        order1.setUser(user);
        LocalDateTime orderDate = new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        order1.setOrderDate(orderDate);
        order1.setStatus(OrderStatus.PENDING);
        Date shippingDate  = order.getShippingDate();
        if (shippingDate!= null || shippingDate.before(new Date())) {
            throw new DataNotFoundException("Data must be at least today");
        }
        order1.setActive(true);
        orderRespository.save(order1);

        return modelMapper.map(order1,OrderResponse.class);
    }

    @Override
    public OrderResponse getOrder(Long id) {
        return null;
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderDTO order) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderResponse> getAllOrders(Long userId) {
        return List.of();
    }
}
