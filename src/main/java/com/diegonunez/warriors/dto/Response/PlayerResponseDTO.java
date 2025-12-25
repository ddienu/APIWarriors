package com.diegonunez.warriors.dto.Response;

import java.util.List;

public class PlayerResponseDTO {

    private Integer playerId;
    private String nickname;
    private List<WarriorResponseDTO> warriorsSelected;
    private UserResponseDTO user;
    private Integer points;
    private Integer gamesWon;
    private Integer gamesLost;

    public PlayerResponseDTO(Integer playerId, String nickname, List<WarriorResponseDTO> warriorsSelected, UserResponseDTO user, Integer points,
                             Integer gamesWon, Integer gamesLost) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.warriorsSelected = warriorsSelected;
        this.user = user;
        this.points = points;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Integer getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(Integer gamesLost) {
        this.gamesLost = gamesLost;
    }
}
