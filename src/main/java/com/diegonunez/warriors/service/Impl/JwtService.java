package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Auth.request.LoginRequestDTO;
import com.diegonunez.warriors.entity.User;
import com.diegonunez.warriors.repository.IUserRepository;
import com.diegonunez.warriors.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements IJwtService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    private final IUserRepository userRepository;

    public JwtService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String getToken(LoginRequestDTO user){
        return generateToken(new HashMap<>(), user);
    }
    @Override
    public String generateToken(Map<String, Object> extraClaims, LoginRequestDTO user) {
        User userFounded = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userFounded.getUserId().toString())
                .claim("email", userFounded.getEmail())
                .claim("role", userFounded.getRole().getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }

    @Override
    public Key getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getEmailFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    @Override
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
