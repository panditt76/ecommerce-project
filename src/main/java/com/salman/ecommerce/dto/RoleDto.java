package com.salman.ecommerce.dto;

import com.salman.ecommerce.entity.RoleName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleDto {

    private long roleId;
    private RoleName name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
