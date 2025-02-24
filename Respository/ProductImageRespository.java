package com.project.shopapp.Respository;
import com.project.shopapp.MODELS.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRespository extends JpaRepository<ProductImage,Long>{
    List<ProductImage> findProductImageById(Long productId);


}
