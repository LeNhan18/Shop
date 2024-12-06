package com.project.shopapp.DTOS;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {
    @JsonProperty("orderid")
    @Min(value = 1,message = "The orderid must be > 0")
    private Long orderId;

    @JsonProperty("productid")
    @Min(value = 1,message = "The productid must be > 0")
    private Long productId;

    @Min(value= 1, message ="Price must be > 0")
    private double price;

    @JsonProperty("number_of_products")
    @Min(value = 1,message = "The number of products must be >0 ")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private double totalMoney;

    private String color;

}
