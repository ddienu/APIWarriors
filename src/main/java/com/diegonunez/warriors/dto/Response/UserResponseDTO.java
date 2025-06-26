package com.diegonunez.warriors.dto.Response;

public class UserResponseDTO {

    private Integer userId;
    private String email;
    private String password;
    private UserStatusResponseDTO userStatus;
    private RoleResponseDTO role;

    public UserResponseDTO(Integer userId, String email, String password, UserStatusResponseDTO userStatus, RoleResponseDTO role){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public UserStatusResponseDTO getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusResponseDTO userStatus) {
        this.userStatus = userStatus;
    }

    public RoleResponseDTO getRole() {
        return role;
    }

    public void setRole(RoleResponseDTO role) {
        this.role = role;
    }
}
