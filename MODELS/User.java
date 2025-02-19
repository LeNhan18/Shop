package com.project.shopapp.MODELS;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="fullname",nullable = false,length =100)
    private String fullname;
    @Column(name ="phone_number",nullable = false,length =10)
    private String phoneNumber;
    @Column(name ="email",nullable = false,length =100, unique = true)
    private String email;
    @Column(name ="address",nullable = false,length =200)
    private String address;
    @Column(name ="password",nullable = false,length =100)
    private String password;
    @Column(name ="is_active")
    private Boolean isActive;
    @Column(name ="date_of_birth",nullable = false)
    private Date dateOfBirth;
    @Column(name ="facebook_account")
    private int facebookAccount;
    @Column(name ="google_account")
    private int googleAccount;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
