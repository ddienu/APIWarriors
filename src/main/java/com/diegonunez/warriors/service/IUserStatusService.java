package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.UserStatusRequestDTO;
import com.diegonunez.warriors.dto.Response.UserStatusResponseDTO;

import java.util.List;

public interface IUserStatusService {

    List<UserStatusResponseDTO> getAllUserStatus();
    UserStatusResponseDTO getUserStatusById(Integer userStatusId);
    UserStatusResponseDTO createUserStatus(UserStatusRequestDTO newUserStatus);
    UserStatusResponseDTO updateUserStatus(Integer userStatusId, UserStatusRequestDTO userStatusUpdated);
    Boolean deleteUserStatus(Integer userStatusId);
}
