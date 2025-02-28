package com.project.shopapp.Respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRespone extends BaseRespone {
    private String productName;
    private Float price;
    private String thumbnail;
    private String description;
    private String title;
    @JsonProperty("category_id")
    private Long categoryId;

}
