package com.project.shopapp.Respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.MODELS.Product;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRespone extends BaseRespone {
    private String productName;
    private Float price;
    private String thumbnail;
    private String description;
    private String title;
    @JsonProperty("category_id")
    private Long categoryId;
    public static ProductRespone fromProduct (Product product){
        ProductRespone productRespone = ProductRespone.builder()
                .productName(product.getName())
                .title(product.getTitle())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .categoryId(product.getCategoryId().getId())
                .build();
        productRespone.setCreatedAt(product.getCreatedAt());
        productRespone.setUpdatedAt(product.getUpdatedAt());
        return productRespone;
    }

}
