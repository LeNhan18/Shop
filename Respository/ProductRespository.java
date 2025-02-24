package com.project.shopapp.Respository;

import com.project.shopapp.MODELS.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<Product,Long> {
    boolean existsByTitle(String title);
    boolean existsByName(String name);
    Page<Product> findAll(Pageable pageable);//Phân trang

}
