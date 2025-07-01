package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Auth.request.LoginRequestDTO;
import com.diegonunez.warriors.dto.Auth.request.RegisterRequestDTO;
import com.diegonunez.warriors.dto.Auth.response.AuthResponseDTO;
import com.diegonunez.warriors.dto.Auth.response.RegisterResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    ResponseEntity<ApiResponse<RegisterResponseDTO>> register(RegisterRequestDTO registerPayload);
    ResponseEntity<ApiResponse<AuthResponseDTO>> login(LoginRequestDTO loginPayload);
}