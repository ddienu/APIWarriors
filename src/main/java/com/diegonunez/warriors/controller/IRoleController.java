package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.RoleRequestDTO;
import com.diegonunez.warriors.dto.Response.RoleResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleController {
    ResponseEntity<ApiResponse<RoleResponseDTO>> getRoleById(Integer roleId);
    ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getRoles();
    ResponseEntity<ApiResponse<RoleResponseDTO>> createRole(RoleRequestDTO newRole);
    ResponseEntity<ApiResponse<RoleResponseDTO>> updateRole(Integer roleId, RoleRequestDTO roleUpdated);
    ResponseEntity<ApiResponse<Boolean>> deleteRole(Integer roleId);

}
