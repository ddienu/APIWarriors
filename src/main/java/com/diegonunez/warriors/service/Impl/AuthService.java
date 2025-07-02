package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Auth.request.LoginRequestDTO;
import com.diegonunez.warriors.dto.Auth.request.RegisterRequestDTO;
import com.diegonunez.warriors.dto.Auth.response.AuthResponseDTO;
import com.diegonunez.warriors.dto.Auth.response.RegisterResponseDTO;
import com.diegonunez.warriors.entity.Role;
import com.diegonunez.warriors.entity.User;
import com.diegonunez.warriors.entity.UserStatus;
import com.diegonunez.warriors.exception.Unchecked.InvalidCredentialsException;
import com.diegonunez.warriors.repository.IRoleRepository;
import com.diegonunez.warriors.repository.IUserRepository;
import com.diegonunez.warriors.repository.IUserStatusRepository;
import com.diegonunez.warriors.service.IAuthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;
    private final IUserStatusRepository userStatusRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder,
                       IRoleRepository roleRepository, IUserStatusRepository userStatusRepository, JwtService jwtService,
                       AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userStatusRepository = userStatusRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO requestPayload) {
        User newUser = new User();
        newUser.setEmail(requestPayload.getEmail());
        newUser.setPassword(encodePassword(requestPayload.getPassword()));
        newUser.setRole(findRoleById(3));
        newUser.setUserStatus(findUserStatusById(2));

        userRepository.save(newUser);

        return new RegisterResponseDTO(
            newUser.getEmail()
        );
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginPayload) {
/*        User userFounded = userRepository.findByEmail(loginPayload.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User with email: "+loginPayload.getEmail()+" not found")
        );

        Boolean passwordMatch = matchesPassword(loginPayload.getPassword(), userFounded.getPassword());
        if(Boolean.FALSE.equals(passwordMatch)){
            throw new InvalidCredentialsException("Invalid credentials, please check your email or password");
        }*/
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginPayload.getEmail(),
                            loginPayload.getPassword()
                    )
            );
        }catch(BadCredentialsException e){
            throw new InvalidCredentialsException("Invalid credentials, please check your email or password");
        }


        return new AuthResponseDTO(
                loginPayload.getEmail(),
                jwtService.getToken(loginPayload)
        );

    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private Boolean matchesPassword(String password, String passwordDb){
        return passwordEncoder.matches(password, passwordDb);
    }

    private Role findRoleById(Integer roleId){
        return roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role with ID: "+roleId+" not found")
        );
    }

    private UserStatus findUserStatusById(Integer userStatusId){
        return userStatusRepository.findById(userStatusId).orElseThrow(
                () -> new EntityNotFoundException("User status with ID: "+userStatusId+" not found")
        );
    }
}
