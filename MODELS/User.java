package com.project.shopapp.MODELS;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
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
    private Integer facebookAccount;
    @Column(name ="google_account")
    private Integer googleAccount;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
