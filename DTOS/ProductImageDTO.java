package com.project.shopapp.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
    @JsonProperty("product_id")
    @Min(value = 1 ,message ="Product must ID >1 ")
    private Long productId ;

    @Size(min = 5, max = 200, message = "Image name")
    @JsonProperty("image_url")
    private String imageUrl;


}
