package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRespository extends JpaRepository<OrderDetail,Long>{
    List<OrderDetail>findOrderDetailId(Long orderId);
}
