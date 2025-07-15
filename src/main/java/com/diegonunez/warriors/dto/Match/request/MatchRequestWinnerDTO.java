package com.diegonunez.warriors.dto.Match.request;

public class MatchRequestWinnerDTO {

    private Integer matchId;
    private Integer[] playersIds;

    public MatchRequestWinnerDTO(Integer matchId, Integer[] playersIds) {
        this.matchId = matchId;
        this.playersIds = playersIds;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer[] getPlayersIds() {
        return playersIds;
    }

    public void setPlayersIds(Integer[] playersIds) {
        this.playersIds = playersIds;
    }
}
