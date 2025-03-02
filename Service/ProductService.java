package com.project.shopapp.Service;

import com.project.shopapp.DTOS.ProductDTO;
import com.project.shopapp.DTOS.ProductImageDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.Exception.InvalidParamException;
import com.project.shopapp.MODELS.Category;
import com.project.shopapp.MODELS.Product;
import com.project.shopapp.MODELS.ProductImage;
import com.project.shopapp.Respones.ProductRespone;
import com.project.shopapp.Respository.CategoryRespository;
import com.project.shopapp.Respository.ProductImageRespository;
import com.project.shopapp.Respository.ProductRespository;
import com.project.shopapp.Service.IMP.IMPProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IMPProductService {
    private final ProductRespository productRespository;
    private final CategoryRespository categoryRespository;
    private final ProductImageRespository productImageRespository;
    @Override
    public Product CreateProduct(ProductDTO productDTO) throws DataNotFoundException{
        Category existingCategory =categoryRespository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new DataNotFoundException("Cannot find with id"));
        Product product = Product.builder()
                .name(productDTO.getProductName())
                .price(productDTO.getPrice())
                .title(productDTO.getTitle())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .categoryId(existingCategory)
                .build();
        Product savedProduct = productRespository.save(product);
        return savedProduct;
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        return productRespository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Product not found"));
    }

    @Override
    public Page<ProductRespone> getAllProduct(PageRequest a ) {
        return productRespository.findAll(a).map(ProductRespone ::fromProduct);
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
            existingProduct.setTitle(productDTO.getTitle());
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
    @Override
    public ProductImage createProductImage(Long id , ProductImageDTO productImage) throws Exception{
        Product existingProduct =productRespository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Cannot find with id"));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImage.getImageUrl())
                .build();
        // Không cho insert quá 5 ảnh
        int size =productImageRespository.findProductImageById(id).size();
        if(size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new InvalidParamException("Numbers of image must be <= "
            +ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRespository.save(newProductImage);
    }

}
