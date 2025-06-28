package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PlayerRequestDTO {

    @NotNull(message = "Player nickname cannot be null")
    @NotEmpty(message = "Player nickname cannot be empty")
    private String nickname;
    @NotNull(message = "Player's warriors selected cannot be null")
    @Size(min = 5, max = 5, message = "The player must have five warriors")
    private List<Integer> warriorsIdSelected;
    @NotNull(message = "The user id cannot be null")
    private Integer userId;

    public PlayerRequestDTO(String nickname, List<Integer> warriorsIdSelected, Integer userId) {
        this.nickname = nickname;
        this.warriorsIdSelected = warriorsIdSelected;
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Integer> getWarriorsIdSelected() {
        return warriorsIdSelected;
    }

    public void setWarriorsIdSelected(List<Integer> warriorsIdSelected) {
        this.warriorsIdSelected = warriorsIdSelected;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
