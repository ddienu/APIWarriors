package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IUserStatusController;
import com.diegonunez.warriors.dto.Request.UserStatusRequestDTO;
import com.diegonunez.warriors.dto.Response.UserStatusResponseDTO;
import com.diegonunez.warriors.service.Impl.UserStatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "v1/userstatus")
public class UserStatusController implements IUserStatusController {

    private final UserStatusService userStatusService;

    public UserStatusController(UserStatusService userStatusService){
        this.userStatusService = userStatusService;
    }
    @GetMapping
    @Override
    public ResponseEntity<ApiResponse<List<UserStatusResponseDTO>>> findAllUserStatus() {
        List<UserStatusResponseDTO> response = userStatusService.getAllUserStatus();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User status retrieved successfully",
                        response
                )
        );
    }

    @GetMapping(path = "/{userStatusId}")
    @Override
    public ResponseEntity<ApiResponse<UserStatusResponseDTO>> findUserStatusById(@PathVariable Integer userStatusId) {
        UserStatusResponseDTO response = userStatusService.getUserStatusById(userStatusId);

        return ResponseEntity.status(HttpStatus.OK).body(
                 new ApiResponse<>(
                         "User status with ID: "+userStatusId+" retrieved successfully",
                         response
                 )
        );
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponse<UserStatusResponseDTO>> createUserStatus(@Valid @RequestBody UserStatusRequestDTO newUserStatus) {
        UserStatusResponseDTO response = userStatusService.createUserStatus(newUserStatus);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User status created successfully",
                        response
                )
        );
    }

    @PutMapping(path = "/{userStatusId}")
    @Override
    public ResponseEntity<ApiResponse<UserStatusResponseDTO>> updateUserStatus(@PathVariable Integer userStatusId, @Valid @RequestBody UserStatusRequestDTO userStatusUpdated) {
        UserStatusResponseDTO response = userStatusService.updateUserStatus(userStatusId, userStatusUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User status with ID: "+userStatusId+" updated successfully",
                        response
                )
        );
    }

    @DeleteMapping(path = "/{userStatusId}")
    @Override
    public ResponseEntity<ApiResponse<Boolean>> deleteUserStatus(@PathVariable Integer userStatusId) {
        Boolean response = userStatusService.deleteUserStatus(userStatusId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "User status with ID: "+userStatusId+" deleted successfully",
                        response
                )
        );
    }
}
