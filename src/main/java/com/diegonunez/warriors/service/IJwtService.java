package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Auth.request.LoginRequestDTO;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJwtService {
    String getToken(LoginRequestDTO user);
    String generateToken(Map<String, Object> extraClaims, LoginRequestDTO user);
    String getEmailFromToken(String token);
    Key getKey();
    boolean isTokenValid(String token, UserDetails userDetails);
    Date getExpirationDate(String token);
    boolean isTokenExpired(String token);
    <T> T getClaim(String token, Function<Claims, T> claimsResolver);
    Claims getClaims(String token);
}
