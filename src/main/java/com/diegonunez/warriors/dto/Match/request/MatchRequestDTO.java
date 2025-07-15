package com.diegonunez.warriors.dto.Match.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MatchRequestDTO {
    @NotNull(message = "CreatedBy id cannot be null")
    private Integer createdByUserId;
    @NotNull(message = "Match's name cannot be null")
    @NotEmpty(message = "Match's name cannot be empty")
    private String name;
    /*@NotNull(message = "The player's ids cannot be null")
    private List<Integer> playersId;*/

    public MatchRequestDTO( Integer createdByUserId, String name) {
        this.createdByUserId = createdByUserId;
        this.name = name;
    }
    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
