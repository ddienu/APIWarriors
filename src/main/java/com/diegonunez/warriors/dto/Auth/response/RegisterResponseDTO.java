package com.diegonunez.warriors.dto.Auth.response;

public class RegisterResponseDTO {
    private String email;

    public RegisterResponseDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
