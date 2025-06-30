package com.diegonunez.warriors.dto.Match.response;

public class MatchUserResponseDTO {
    private Integer userId;
    private String email;

    public MatchUserResponseDTO(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
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
}
