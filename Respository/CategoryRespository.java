package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.Category;
import com.project.shopapp.MODELS.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRespository extends JpaRepository<Category,Long>{
      List<OrderDetail> FindOrderDetailId(Long orderId);

}
