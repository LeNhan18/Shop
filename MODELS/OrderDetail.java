package com.project.shopapp.MODELS;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="token")
    private String token;
    @Column(name ="token_type")
    private String tokenType;
    @Column(name ="expiration")
    private LocalDateTime expiration;
    @Column(name ="revoked")
    private Boolean revoked;
    @Column(name ="expired")
    private Boolean expired;
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
}
