package com.recipe.universe.domain.user.jwt.service;

import com.recipe.universe.domain.user.jwt.dto.JwtTokenDto;
import com.recipe.universe.domain.user.oauth2.dto.CustomOidcUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenService {
    private final Key KEY;
    private final long ACCESS_EXPIRATION = 24*60*60*1000; // 하루
    private final long REFRESH_EXPIRATION = 14*24*60*60*1000; // 2주
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final JwtParser parser;

    public JwtTokenService(@Value("${jwt.secret}") String secretKey){
        byte[] key = Decoders.BASE64.decode(secretKey);
        this.KEY = Keys.hmacShaKeyFor(key);
        parser = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build();
    }

    public String generateToken(Authentication authentication){
        CustomOidcUser user = (CustomOidcUser) authentication.getPrincipal();
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        long now = new Date().getTime();
        Date accessExpiriation = new Date(now + ACCESS_EXPIRATION);
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("provider", user.getProvider())
                .claim("authorities", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(accessExpiriation)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public JwtTokenDto generateToken(String userId){
        long now = new Date().getTime();
        Date accessExpiration = new Date(now + ACCESS_EXPIRATION);
        Date refreshExpiration = new Date(now + REFRESH_EXPIRATION);

        String accessToken = Jwts.builder()
                .setSubject(userId)
                .claim("userId", userId)
                .setExpiration(accessExpiration)
                .signWith(KEY, signatureAlgorithm)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .claim("userId", userId)
                .claim("isRefresh", true)
                .setExpiration(refreshExpiration)
                .signWith(KEY, signatureAlgorithm)
                .compact();

        return new JwtTokenDto(
            accessToken,
            refreshToken,
            accessExpiration,
            refreshExpiration
        );
    }

    public Claims validateToken(String token){
        try {
            Claims claims = parser
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        }catch (RuntimeException e){
            throw e;
        }
    }

    /* utils */

    public String getToken(String authHeader){
        return authHeader.split(" ")[1];
    }

    public boolean isBearer(String authHeader){
        return authHeader.startsWith("Bearer ");
    }

    /* Basic Utils */

    public boolean isBasic(String authHeader){
        return authHeader.startsWith("Basic ");
    }

    public String[] decodeBasicToken(String token){
        byte[] decodeByte = Base64.getDecoder().decode(token);
        String decodedToken = new String(decodeByte);
        return decodedToken.split(":");
    }
}
