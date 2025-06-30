package com.salman.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuperAdminDetailDto {
    private String email;
    private String username;
    private String role;

}
