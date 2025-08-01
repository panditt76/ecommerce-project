package com.salman.ecommerce.security;

import com.salman.ecommerce.entity.TokenBlacklist;
import com.salman.ecommerce.repository.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtTokenBlacklist {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    public void addToBlacklist(String token) {
        tokenBlacklistRepository.save(new TokenBlacklist(token, LocalDateTime.now()));
    }

    public boolean isBlacklisted(String token) {
        return tokenBlacklistRepository.existsById(token);
    }
}
