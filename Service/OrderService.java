package com.project.shopapp.Service;

import com.project.shopapp.DTOS.OrderDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.Order;
import com.project.shopapp.MODELS.OrderStatus;
import com.project.shopapp.MODELS.User;
import com.project.shopapp.Respository.OrderRespository;
import com.project.shopapp.Respository.UserRespository;
import com.project.shopapp.Service.IMP.IMPOrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IMPOrderService{
    private final UserRespository userRespository;
    private final OrderRespository orderRespository;
    private final  ModelMapper modelMapper;
    @SneakyThrows
    @Override
    public Order createOrder(OrderDTO order) {
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
        LocalDate shippingDate = order.getShippingDate() == null ? LocalDate.now():order.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Data must be at least today");
        }
        order1.setShippingDate(shippingDate);
        order1.setActive(true);
        orderRespository.save(order1);
        return order1;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRespository.findById(id).orElse(null);
    }

    @SneakyThrows
    @Override
    public Order updateOrder(Long id, OrderDTO order) {
        Order order1 = orderRespository.findById(id).orElseThrow(()->
                new DataNotFoundException("Cannot find order with id "+id));
        User existingUser = userRespository.findById(order.getUserId()).orElseThrow(()->
                new DataNotFoundException("Cannot find order by user id"+ id));
        //Tạo một luồng ánh xạ riêng để kiểm soát việc ánh xạ
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper ->mapper.skip(Order::setId));
        //Cập nhật các trường của đơn hàng từ OrderDTO
        modelMapper.map(order, order1);
        order1.setUser(existingUser);
        return orderRespository.save(order1);
    }

    @Override
    public void deleteOrder(Long id) {
        Order orders = orderRespository.findById(id).orElse(null);
        //Không xóa cứng hãy xóa mềm
        if (orders != null) {
            orders.setActive(false);
            orderRespository.save(orders);
        }
    }

    @Override
    public List<Order> findbyUserid(Long userId) {
        return orderRespository.findByUserId(userId);
    }
}
