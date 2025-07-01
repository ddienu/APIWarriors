package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Auth.request.LoginRequestDTO;
import com.diegonunez.warriors.dto.Auth.request.RegisterRequestDTO;
import com.diegonunez.warriors.dto.Auth.response.AuthResponseDTO;
import com.diegonunez.warriors.dto.Auth.response.RegisterResponseDTO;

public interface IAuthService {

    RegisterResponseDTO register(RegisterRequestDTO requestPayload);
    AuthResponseDTO login(LoginRequestDTO loginPayload);

}
