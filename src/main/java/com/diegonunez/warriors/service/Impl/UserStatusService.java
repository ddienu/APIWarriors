package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.UserStatusRequestDTO;
import com.diegonunez.warriors.dto.Response.UserStatusResponseDTO;
import com.diegonunez.warriors.entity.UserStatus;
import com.diegonunez.warriors.repository.IUserStatusRepository;
import com.diegonunez.warriors.service.IUserStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserStatusService implements IUserStatusService {

    private final IUserStatusRepository userStatusRepository;

    public UserStatusService(IUserStatusRepository userStatusRepository){
        this.userStatusRepository = userStatusRepository;
    }
    @Override
    public List<UserStatusResponseDTO> getAllUserStatus() {
        return userStatusRepository.findAll().stream().map(
                userStatus -> new UserStatusResponseDTO(
                        userStatus.getId(),
                        userStatus.getName(),
                        userStatus.getDescription()
                )
        ).toList();
    }

    @Override
    public UserStatusResponseDTO getUserStatusById(Integer userStatusId) {

        UserStatus userStatusFounded = userStatusRepository.findById(userStatusId).orElseThrow(
                () -> new EntityNotFoundException("User status with ID: "+userStatusId+" not founded")
        );

        return new UserStatusResponseDTO(
                userStatusFounded.getId(),
                userStatusFounded.getName(),
                userStatusFounded.getDescription()
        );
    }

    @Transactional
    @Override
    public UserStatusResponseDTO createUserStatus(UserStatusRequestDTO newUserStatus) {
        UserStatus newStatus = new UserStatus();
        newStatus.setName(newUserStatus.getName());
        newStatus.setDescription(newUserStatus.getDescription());

        userStatusRepository.save(newStatus);

        return new UserStatusResponseDTO(
                newStatus.getId(),
                newStatus.getName(),
                newUserStatus.getDescription()
        );
    }

    @Override
    public UserStatusResponseDTO updateUserStatus(Integer userStatusId, UserStatusRequestDTO userStatusUpdated) {
        UserStatus userStatusFounded = userStatusRepository.findById(userStatusId).orElseThrow(
                () -> new EntityNotFoundException("User status with ID: "+userStatusId+" not founded")
        );

        if(!userStatusFounded.getName().equals(userStatusUpdated.getName())){
            userStatusFounded.setName(userStatusUpdated.getName());
        }

        if(!userStatusFounded.getDescription().equals(userStatusUpdated.getDescription())){
            userStatusFounded.setDescription(userStatusUpdated.getDescription());
        }

        userStatusRepository.save(userStatusFounded);

        return new UserStatusResponseDTO(
                userStatusFounded.getId(),
                userStatusFounded.getName(),
                userStatusFounded.getDescription()
        );
    }

    @Override
    public Boolean deleteUserStatus(Integer userStatusId) {
        UserStatus userStatusFounded = userStatusRepository.findById(userStatusId).orElseThrow(
                () -> new EntityNotFoundException("User status with ID: "+userStatusId+" not founded")
        );

        userStatusRepository.delete(userStatusFounded);

        return true;
    }
}
