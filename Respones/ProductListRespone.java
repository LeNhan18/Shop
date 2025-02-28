package com.project.shopapp.Respones;

import com.project.shopapp.MODELS.Product;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListRespone {
    private List<ProductRespone> listProducts;
    private int total ;
}
