package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IAuthController;
import com.diegonunez.warriors.dto.Auth.request.LoginRequestDTO;
import com.diegonunez.warriors.dto.Auth.request.RegisterRequestDTO;
import com.diegonunez.warriors.dto.Auth.response.AuthResponseDTO;
import com.diegonunez.warriors.dto.Auth.response.RegisterResponseDTO;
import com.diegonunez.warriors.service.Impl.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/auth")
public class AuthController implements IAuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    @Override
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO registerPayload) {
        RegisterResponseDTO serviceResponse = authService.register(registerPayload);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User created successfully",
                        serviceResponse
                )
        );
    }

    @PostMapping(path = "/login")
    @Override
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginPayload) {
        AuthResponseDTO serviceResponse = authService.login(loginPayload);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Login success",
                        serviceResponse
                )
        );
    }
}
