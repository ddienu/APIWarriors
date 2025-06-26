package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.UserRequestDTO;
import com.diegonunez.warriors.dto.Response.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO findUserById(Integer userId);
    List<UserResponseDTO> findAllUsers();
    UserResponseDTO createUser(UserRequestDTO newUser);
    UserResponseDTO updateUser(Integer userId, UserRequestDTO userUpdated);
    Boolean deleteUser(Integer userId);
}
