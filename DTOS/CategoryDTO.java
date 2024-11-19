package com.project.shopapp.DTOS;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
     @NotEmpty(message ="Category's name can not be empty")
    private String name;

}
