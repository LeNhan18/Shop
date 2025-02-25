package com.project.shopapp.MODELS;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,length = 350)
    private String name;

    private Float price;
    @Column(name = "thumbnail",length = 300)
    private String thumbnail;

    @Column(name = "description")
    private String description;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
}
