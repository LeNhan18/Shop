package com.project.shopapp.DTOS;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {
    @JsonProperty("username")
    @NotBlank(message = "Username cannot be blank")
    private String phoneNumber;

    @JsonProperty("password")
    @NotBlank(message = "password cannot be blank")
    private String password;
}
