package com.salman.ecommerce.api;

import com.salman.ecommerce.dto.*;
import com.salman.ecommerce.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerApi {

    private final SellerService sellerService;

    @GetMapping("/current")
    @PreAuthorize("hasRole('SELLER')")
    public SellerDto getCurrentSeller() {
        return sellerService.getCurrentSeller();
    }

    @GetMapping("/{sellerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public SellerDto getSellerById(@PathVariable long sellerId) {
        return sellerService.getSellerById(sellerId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public SellersResponseDto getSellers() {
        return sellerService.getSellers();
    }

    @PutMapping("/current")
    @PreAuthorize("hasRole('SELLER')")
    public SellerDto updateCurrentSeller(@Valid @RequestBody SellerRequestDto sellerRequestDto) {
        return sellerService.updateCurrentSeller(sellerRequestDto);
    }

    @PutMapping("/current/password")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseDto updateCurrentSellerPassword(@Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return sellerService.updateCurrentSellerPassword(changePasswordRequestDto);
    }

    @DeleteMapping("/{sellerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseDto deleteSellerById(@PathVariable long sellerId) {
        return sellerService.deleteSellerById(sellerId);
    }
}
