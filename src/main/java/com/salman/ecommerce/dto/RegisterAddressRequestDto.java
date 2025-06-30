package com.salman.ecommerce.dto;

import com.salman.ecommerce.entity.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterAddressRequestDto extends AddressRequestDto {

    @NotNull
    private AddressType type;
}
