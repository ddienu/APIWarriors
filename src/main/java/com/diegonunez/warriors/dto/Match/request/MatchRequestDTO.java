package com.diegonunez.warriors.dto.Match.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MatchRequestDTO {
    @NotNull(message = "CreatedBy id cannot be null")
    private Integer createdByUserId;
    @NotNull(message = "The player's ids cannot be null")
    private List<Integer> playersId;

    public MatchRequestDTO( Integer createdByUserId, List<Integer> playersId) {
        this.createdByUserId = createdByUserId;
        this.playersId = playersId;
    }
    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public List<Integer> getPlayersId() {
        return playersId;
    }

    public void setPlayersId(List<Integer> playersId) {
        this.playersId = playersId;
    }
}
