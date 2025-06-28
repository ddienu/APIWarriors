package com.diegonunez.warriors.dto.Response;

import java.util.List;

public class PlayerResponseDTO {

    Integer playerId;
    String nickname;
    List<WarriorResponseDTO> warriorsSelected;
    UserResponseDTO user;

    public PlayerResponseDTO(Integer playerId, String nickname, List<WarriorResponseDTO> warriorsSelected, UserResponseDTO user) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.warriorsSelected = warriorsSelected;
        this.user = user;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<WarriorResponseDTO> getWarriorsSelected() {
        return warriorsSelected;
    }

    public void setWarriorsSelected(List<WarriorResponseDTO> warriorsSelected) {
        this.warriorsSelected = warriorsSelected;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
