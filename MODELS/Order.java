package com.project.shopapp.MODELS;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname",nullable = false,length =100)
    private String fullName;

    @Column(name ="email",nullable = false,length =100)
    private String email;

    @Column(name ="phone_number",nullable = false,length =20)
    private String phoneNumber;

    @Column(name ="address",nullable = false,length =200)
    private String address;

    @Column(name ="note",nullable = true,length =100)
    private String note;

    @Column(name ="order_date")
    private LocalDateTime orderDate;

    @Column(name ="total_money")
    private Float totalMoney;

    @Column(name ="status")
    private String status;

    @Column(name ="shipping_method")
    private String shippingMethod;

    @Column(name ="shipping_fee")
    private String shippingAddress;

    @Column(name ="shipping_date")
    private LocalDate shippingDate;

    @Column(name ="tracking_number")
    private String trackingNumber;

    @Column(name ="payment_method")
    private String paymentMethod;

    @Column(name="active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
