package com.project.shopapp.MODELS;

import jakarta.persistence.*;
import jakarta.websocket.OnOpen;
import lombok.*;

@Entity
@Data
@Table(name ="social_accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider",nullable = false,length=20)
    private String provider;

    @Column(name = "provider_id",nullable = false,length=50)
    private String providerId;

    @Column(name = "name" ,length= 150)
    private String name;

    @Column(name = "email",length = 150)
    private String emaill;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
