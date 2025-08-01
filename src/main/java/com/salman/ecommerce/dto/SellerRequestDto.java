package com.salman.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SellerRequestDto {

    private String firstName;
    private String lastName;
    private String mobile;

    @Email
    private String email;

    @NotBlank
    private String username;
}
