package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.Category;
import com.project.shopapp.MODELS.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRespository extends JpaRepository<Category,Long>{
       Optional<Category> findById(Long categoryId);

}
