package com.project.shopapp.DTOS;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {
    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String email;


    private String address;

    @NotBlank(message = "password cannot be blank")
    private String password;

    private String retypePassword;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    
    @JsonProperty("facebook_account_id")
    private int facebookAccountId;

    @JsonProperty("google_account_id")
    private int googleAccountId;

    @JsonProperty("role_id")
    @NotNull(message = "Role Id is required")
    private Long roleId;


}
