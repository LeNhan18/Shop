package com.project.shopapp.Respones;

import com.project.shopapp.MODELS.Order;
import com.project.shopapp.MODELS.OrderDetail;
import com.project.shopapp.MODELS.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDetailResponse {
    private Long id;
    @ManyToOne
    @JoinColumn(name ="order_id")
    private Long order;

    @ManyToOne
    @JoinColumn(name ="product_id")
    private Long product;

    private Float price;

    private int numberOfProducts;

    private Double totalMoney;

    @Column(name = "color")
    private String color;
    public static OrderDetailResponse fromOrder(OrderDetail orderDetail)
    {
        return OrderDetailResponse.builder()
               .id(orderDetail.getId())
               .order(orderDetail.getOrder().getId())
               .product(orderDetail.getProduct().getId())
               .price(orderDetail.getPrice())
               .numberOfProducts(orderDetail.getNumberOfProducts())
               .totalMoney(orderDetail.getTotalMoney())
               .color(orderDetail.getColor())
               .build();
    }
}
