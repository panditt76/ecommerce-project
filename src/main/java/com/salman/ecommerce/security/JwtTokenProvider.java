package com.salman.ecommerce.security;

import com.salman.ecommerce.dto.SuperAdminDetailDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${app.jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${app.jwt.expiration-in-ms}")
    private long expirationInMs;


    //this add for superadmin token
    public String generateTokenForSuperAdmin(SuperAdminDetailDto superAdminDetail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_SUPERADMIN");
        claims.put("details", superAdminDetail);

        return Jwts.builder()
                .claims(claims)
                .subject(superAdminDetail.getEmail()) // or use getUsername()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationInMs))
                .signWith(getSecretKey())
                .compact();
    }










    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationInMs))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getSecretKey())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT token");
        } catch (SecurityException e) {
            log.error("Security exception");
        } catch (IllegalArgumentException e) {
            log.error("Illegal JWT token");
        }
        return false;
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
