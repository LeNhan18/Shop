package com.project.shopapp.Respones;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseRespone {
    @JsonProperty("CreatedAt")
    private LocalDateTime createdAt;

    @JsonProperty("UpdatedAt")
    private LocalDateTime updatedAt;

}
