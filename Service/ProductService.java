package com.project.shopapp.Service;

import com.project.shopapp.DTOS.ProductDTO;
import com.project.shopapp.DTOS.ProductImageDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.Category;
import com.project.shopapp.MODELS.Product;
import com.project.shopapp.MODELS.ProductImage;
import com.project.shopapp.Respository.CategoryRespository;
import com.project.shopapp.Respository.ProductRespository;
import com.project.shopapp.Service.IMP.IMPProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductService implements IMPProductService {
    private final ProductRespository productRespository;
    private final CategoryRespository categoryRespository;
    @Override
    public Product CreateProduct(ProductDTO productDTO) throws DataNotFoundException{
        Category existingCategory =categoryRespository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new DataNotFoundException("Cannot find with id"));
        Product product = Product.builder()
                .name(productDTO.getProductName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .categoryId(existingCategory)
                .build();
        return productRespository.save(product);
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        return productRespository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Product not found"));
    }

    @Override
    public Page<Product> getAllProduct(PageRequest a ) {
        return productRespository.findAll(a);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(id);
        if(existingProduct != null){
            // Copy cac thuoc tinh tu Product DTO
            //Co the su dung modelMapper
            Category existingCategory =categoryRespository.findById(productDTO.getCategoryId())
                    .orElseThrow(()->new DataNotFoundException("Cannot find with id"));
            existingProduct.setName(existingProduct.getName());
            existingProduct.setCategoryId(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setThumbnail(productDTO.getThumbnail());
        }
        return productRespository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product>optionalProduct =  productRespository.findById(id);
        if(optionalProduct.isPresent()) {
            productRespository.delete(optionalProduct.get());
        }
        productRespository.deleteById(id);
    }

    @Override
    public boolean existByName(String name) {
        return productRespository.existsByName(name);
    }
    private ProductImage createProductImage(Long id , ProductImageDTO productImage) throws Exception{
        Product existingProduct =productRespository.findById(productImage.getProductId())
                .orElseThrow(()->new DataNotFoundException("Cannot find with id"));
        if(existingProduct!= null){

        }
        return null;
    }

}
