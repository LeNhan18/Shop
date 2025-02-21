package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRespository extends JpaRepository<OrderDetail,Long>{
    List<OrderDetail> findOrderDetailById(Long orderId);
}
