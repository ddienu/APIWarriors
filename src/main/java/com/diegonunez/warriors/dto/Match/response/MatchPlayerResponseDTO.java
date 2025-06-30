package com.diegonunez.warriors.dto.Match.response;

public class MatchPlayerResponseDTO {
    private Integer playerId;
    private String nickname;

    public MatchPlayerResponseDTO(Integer playerId, String nickname) {
        this.playerId = playerId;
        this.nickname = nickname;
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
}
