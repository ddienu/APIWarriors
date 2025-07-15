package com.diegonunez.warriors.dto.Request;

public class JoinMatchRequestDTO {

    private Integer matchId;
    private Integer playerId;
    private String matchCode;

    public JoinMatchRequestDTO(Integer matchId, Integer playerId, String matchCode) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.matchCode = matchCode;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }
}
