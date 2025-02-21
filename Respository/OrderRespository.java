package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<Order,Long>{
    //Tìm các đơn hàng của 1 user nảo đó
    List<Order> findByUserId(Long userId);
}
