package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.UserRequestDTO;
import com.diegonunez.warriors.dto.Response.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {
    ResponseEntity<ApiResponse<List<UserResponseDTO>>> findAllUsers();
    ResponseEntity<ApiResponse<UserResponseDTO>> findUserById(Integer userId);
    ResponseEntity<ApiResponse<UserResponseDTO>> createUser(UserRequestDTO newUser);
    ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(Integer userId, UserRequestDTO userUpdated);
    ResponseEntity<ApiResponse<Boolean>> deleteUser(Integer userId);
}
