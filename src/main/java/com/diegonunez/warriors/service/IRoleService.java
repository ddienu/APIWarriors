package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.RoleRequestDTO;
import com.diegonunez.warriors.dto.Response.RoleResponseDTO;

import java.util.List;

public interface IRoleService {
    RoleResponseDTO addRole(RoleRequestDTO newRole);
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO getRoleById(Integer roleId);
    RoleResponseDTO updateRole(Integer roleId, RoleRequestDTO roleUpdated);
    boolean deleteRole(Integer roleId);
}
