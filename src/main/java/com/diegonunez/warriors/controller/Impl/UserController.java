package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IUserController;
import com.diegonunez.warriors.dto.Request.UserRequestDTO;
import com.diegonunez.warriors.dto.Response.UserResponseDTO;
import com.diegonunez.warriors.service.Impl.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/user")
public class UserController implements IUserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> findAllUsers() {
        List<UserResponseDTO> serviceResponse = userService.findAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Users retrieved successfully",
                        serviceResponse
                )
        );
    }

    @GetMapping(path = "/{userId}")
    @Override
    public ResponseEntity<ApiResponse<UserResponseDTO>> findUserById(@PathVariable Integer userId) {
        UserResponseDTO serviceResponse = userService.findUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User with ID: "+userId+" retrieved successfully",
                        serviceResponse
                )
        );
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO newUser) {
        UserResponseDTO serviceResponse = userService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User created successfully",
                        serviceResponse
                )
        );
    }

    @PutMapping(path = "/{userId}")
    @Override
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserRequestDTO userUpdated) {
        UserResponseDTO serviceResponse = userService.updateUser(userId, userUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "User with ID: "+userId+" updated successfully",
                        serviceResponse
                )
        );
    }

    @DeleteMapping(path = "/{userId}")
    @Override
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Integer userId) {
        Boolean serviceResponse = userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "User with ID: "+userId+" deleted successfully",
                        serviceResponse
                )
        );
    }
}
