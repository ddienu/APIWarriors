package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.config.PasswordEncoderConfig;
import com.diegonunez.warriors.dto.Request.UserRequestDTO;
import com.diegonunez.warriors.dto.Response.RoleResponseDTO;
import com.diegonunez.warriors.dto.Response.UserResponseDTO;
import com.diegonunez.warriors.dto.Response.UserStatusResponseDTO;
import com.diegonunez.warriors.entity.Role;
import com.diegonunez.warriors.entity.User;
import com.diegonunez.warriors.entity.UserStatus;
import com.diegonunez.warriors.repository.IRoleRepository;
import com.diegonunez.warriors.repository.IUserRepository;
import com.diegonunez.warriors.repository.IUserStatusRepository;
import com.diegonunez.warriors.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserStatusRepository userStatusRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoderConfig passwordEncoder;

    public UserService(IUserRepository userRepository, IUserStatusRepository userStatusRepository, IRoleRepository roleRepository,
                       PasswordEncoderConfig passwordEncoder){
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO findUserById(Integer userId) {
        User userFounded = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with ID: "+userId+" not founded")
        );

        return new UserResponseDTO(
                userFounded.getUserId(),
                userFounded.getEmail(),
                userFounded.getPassword(),
                new UserStatusResponseDTO(
                        userFounded.getUserStatus().getId(),
                        userFounded.getUserStatus().getName(),
                        userFounded.getUserStatus().getDescription()
                ),
                new RoleResponseDTO(
                        userFounded.getRole().getId(),
                        userFounded.getRole().getName(),
                        userFounded.getRole().getDescription()
                )
        );
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll().stream().map(
                user -> new UserResponseDTO(
                        user.getUserId(),
                        user.getEmail(),
                        user.getPassword(),
                        new UserStatusResponseDTO(
                                user.getUserStatus().getId(),
                                user.getUserStatus().getName(),
                                user.getUserStatus().getDescription()
                        ),
                        new RoleResponseDTO(
                                user.getRole().getId(),
                                user.getRole().getName(),
                                user.getRole().getDescription()
                        )
                )
        ).toList();
    }

    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO newUser) {
        User createdUser = new User();
        UserStatus userStatusFounded = userStatusRepository.findById(newUser.getUserStatusId()).orElseThrow(
                () -> new EntityNotFoundException("User status with ID: "+newUser.getUserStatusId()+" not founded")
        );
        Role roleFounded = roleRepository.findById(newUser.getRoleId()).orElseThrow(
                () -> new EntityNotFoundException("Role with ID: "+newUser.getRoleId()+" not founded")
        );
        createdUser.setEmail(newUser.getEmail());
        createdUser.setPassword(hashPassword(newUser.getPassword()));
        createdUser.setUserStatus(userStatusFounded);
        createdUser.setRole(roleFounded);

        userRepository.save(createdUser);

        return new UserResponseDTO(
                createdUser.getUserId(),
                createdUser.getEmail(),
                createdUser.getPassword(),
                new UserStatusResponseDTO(
                        createdUser.getUserStatus().getId(),
                        createdUser.getUserStatus().getName(),
                        createdUser.getUserStatus().getDescription()
                ),
                new RoleResponseDTO(
                        createdUser.getRole().getId(),
                        createdUser.getRole().getName(),
                        createdUser.getRole().getDescription()
                )
        );
    }

    @Override
    public UserResponseDTO updateUser(Integer userId, UserRequestDTO userUpdated) {
        User userFounded = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with ID: "+userId+" not founded")
        );

        if(!userFounded.getEmail().equals(userUpdated.getEmail())){
            userFounded.setEmail(userUpdated.getEmail());
        }

        if(!userFounded.getPassword().equals(userUpdated.getPassword())){
            userFounded.setPassword(userUpdated.getPassword());
        }

        if(!Objects.equals(userFounded.getUserStatus().getId(), userUpdated.getUserStatusId())){
            UserStatus userStatusFounded = userStatusRepository.findById(userUpdated.getUserStatusId()).orElseThrow(
                    () -> new EntityNotFoundException("User status with ID: "+userUpdated.getUserStatusId()+" not founded")
            );
            userFounded.setUserStatus(userStatusFounded);
        }

        if(Objects.equals(userFounded.getRole().getId(), userUpdated.getRoleId())){
            Role roleFounded = roleRepository.findById(userUpdated.getRoleId()).orElseThrow(
                    () -> new EntityNotFoundException("Role with ID: "+userUpdated.getRoleId()+" not founded")
            );
            userFounded.setRole(roleFounded);
        }

        userRepository.save(userFounded);

        return new UserResponseDTO(
                userFounded.getUserId(),
                userUpdated.getEmail(),
                userUpdated.getPassword(),
                new UserStatusResponseDTO(
                        userFounded.getUserStatus().getId(),
                        userFounded.getUserStatus().getName(),
                        userFounded.getUserStatus().getDescription()
                ),
                new RoleResponseDTO(
                        userFounded.getRole().getId(),
                        userFounded.getRole().getName(),
                        userFounded.getRole().getDescription()
                )
        );
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        User userFounded = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with ID: "+userId+" not founded" )
        );

        userRepository.delete(userFounded);

        return true;
    }

    private String hashPassword(String userPassword){
        return passwordEncoder.passwordEncoder().encode(userPassword);
    }
}
