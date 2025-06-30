package com.salman.ecommerce.api;


import com.salman.ecommerce.dto.*;
import com.salman.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/customer")
    public CustomerDto registerCustomer(@Valid @RequestBody RegisterCustomerRequestDto registerCustomerRequestDto) {
        return authenticationService.registerCustomer(registerCustomerRequestDto);
    }

    @PostMapping("/register/seller")
    public SellerDto registerSeller(@Valid @RequestBody RegisterSellerRequestDto registerSellerRequestDto) {
        return authenticationService.registerSeller(registerSellerRequestDto);
    }

    @PostMapping("/login")
    public AccessTokenDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }

    @PostMapping("/logout")
    public ResponseDto logout(@RequestHeader("Authorization") String authorization) {
        return authenticationService.logout(authorization.substring(7));
    }
}
