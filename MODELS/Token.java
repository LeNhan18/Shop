package com.project.shopapp.MODELS;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;
    @Column(name = "token_type")
    private String tokenType;

    private Boolean revoked;
    private Boolean expired;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
}
