package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.UserStatusRequestDTO;
import com.diegonunez.warriors.dto.Response.UserStatusResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserStatusController {
    ResponseEntity<ApiResponse<List<UserStatusResponseDTO>>> findAllUserStatus();
    ResponseEntity<ApiResponse<UserStatusResponseDTO>> findUserStatusById(Integer userStatusId);
    ResponseEntity<ApiResponse<UserStatusResponseDTO>> createUserStatus(UserStatusRequestDTO newUserStatus);
    ResponseEntity<ApiResponse<UserStatusResponseDTO>> updateUserStatus(Integer userStatusId, UserStatusRequestDTO userStatusUpdated);
    ResponseEntity<ApiResponse<Boolean>> deleteUserStatus(Integer userStatusId);
}
