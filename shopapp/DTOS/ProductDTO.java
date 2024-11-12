package com.project.shopapp.DTOS;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotEmpty(message = "The Product can not be Empty")
    private String productName;
}
