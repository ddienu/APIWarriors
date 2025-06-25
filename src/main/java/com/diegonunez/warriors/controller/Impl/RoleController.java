package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IRoleController;
import com.diegonunez.warriors.dto.Request.RoleRequestDTO;
import com.diegonunez.warriors.dto.Response.RoleResponseDTO;
import com.diegonunez.warriors.service.Impl.RoleService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "v1/role")
public class RoleController implements IRoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping(path = "/{roleId}")
    @Override
    public ResponseEntity<ApiResponse<RoleResponseDTO>> getRoleById(@PathVariable Integer roleId) {
        RoleResponseDTO response = roleService.getRoleById(roleId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Role with ID: "+roleId+" retrieved successfully",
                        response
                )
        );
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getRoles() {
        List<RoleResponseDTO> response = roleService.getAllRoles();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Roles retrieved successfully",
                        response
                )
        );
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponse<RoleResponseDTO>> createRole(@Valid @RequestBody RoleRequestDTO newRole) {
        RoleResponseDTO response = roleService.addRole(newRole);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Role added successfully",
                        response
                )
        );
    }

    @PutMapping(path = "/{roleId}")
    @Override
    public ResponseEntity<ApiResponse<RoleResponseDTO>> updateRole(@PathVariable Integer roleId, @Valid @RequestBody RoleRequestDTO roleUpdated) {
        RoleResponseDTO response = roleService.updateRole(roleId, roleUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Role with ID: "+roleId+" updated successfully",
                        response
                )
        );
    }

    @DeleteMapping(path = "/{roleId}")
    @Override
    public ResponseEntity<ApiResponse<Boolean>> deleteRole(@PathVariable Integer roleId) {
        Boolean response = roleService.deleteRole(roleId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Role with ID: "+roleId+" removed successfully",
                        response
                )
        );
    }
}
