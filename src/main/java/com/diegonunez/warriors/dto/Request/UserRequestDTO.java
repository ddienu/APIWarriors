package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserRequestDTO {
    @NotNull(message = "User email cannot be null")
    @NotEmpty(message = "User email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "User password cannot be null")
    @NotEmpty(message = "User password cannot be empty")
    private String password;
    @NotNull(message = "User status id cannot be null")
    private Integer userStatusId;
    @NotNull(message = "User role id cannot be null")
    private Integer roleId;

    public UserRequestDTO(String email, String password, Integer userStatusId, Integer roleId){
        this.email = email;
        this.password = password;
        this.userStatusId = userStatusId;
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Integer userStatusId) {
        this.userStatusId = userStatusId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
