package com.project.shopapp.Respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderResponse extends BaseRespone{
    private Long id;
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;
    private String note;

    @JsonProperty("order_date")
    private Date orderDate;

    private String status;

    @JsonProperty("total_money")
    private Float totalMoney;

    @Column(name ="shipping_method")
    private String shippingMethod;

    @Column(name ="shipping_fee")
    private String shippingAddress;

    @Column(name ="shipping_date")
    private Date shippingDate;

    @Column(name ="tracking_number")
    private String trackingNumber;

    @Column(name ="payment_method")
    private String paymentMethod;

    @Column(name="active")
    private boolean active;


}
