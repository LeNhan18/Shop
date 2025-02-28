package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.ProductDTO;
import com.project.shopapp.DTOS.ProductImageDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.Product;
import com.project.shopapp.MODELS.ProductImage;
import com.project.shopapp.Respones.ProductRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IMPProductService {
    public Product CreateProduct(ProductDTO productDTO) throws DataNotFoundException;
    Product getProductById(Long id) throws Exception;
    Page<ProductRespone> getAllProduct(PageRequest p);
    Product updateProduct(Long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(Long id);
    boolean existByName(String name);
    public ProductImage createProductImage(Long id , ProductImageDTO productImage) throws Exception;
}
