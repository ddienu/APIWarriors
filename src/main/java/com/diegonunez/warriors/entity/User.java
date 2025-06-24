package com.diegonunez.warriors.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="status_id", nullable = false)
    private UserStatus userStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
