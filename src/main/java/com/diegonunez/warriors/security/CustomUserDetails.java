package com.diegonunez.warriors.security;

import com.diegonunez.warriors.entity.User;
import com.diegonunez.warriors.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomUserDetails implements UserDetailsService {

    private final IUserRepository userRepository;

    public CustomUserDetails(IUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFounded = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User with email: "+email+" not found")
        );

        return new org.springframework.security.core.userdetails.User(
                userFounded.getEmail(),
                userFounded.getPassword(),
                List.of(new SimpleGrantedAuthority(userFounded.getRole().getName()))
        );
    }
}
