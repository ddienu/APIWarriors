package com.diegonunez.warriors.dto.Match.response;

import com.diegonunez.warriors.dto.Response.PlayerResponseDTO;
import com.diegonunez.warriors.dto.Response.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class MatchResponseDTO {
    private Integer id;
    private String code;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private MatchUserResponseDTO createdBy;
    private List<MatchPlayerResponseDTO> players;
    private Integer maxPlayers;
    private MatchPlayerResponseDTO winner;

    public MatchResponseDTO(Integer id, String code, Boolean isActive, LocalDateTime createdAt, MatchUserResponseDTO createdBy, List<MatchPlayerResponseDTO> players, Integer maxPlayers, MatchPlayerResponseDTO winner) {
        this.id = id;
        this.code = code;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.players = players;
        this.maxPlayers = maxPlayers;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MatchUserResponseDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(MatchUserResponseDTO createdBy) {
        this.createdBy = createdBy;
    }

    public List<MatchPlayerResponseDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<MatchPlayerResponseDTO> players) {
        this.players = players;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public MatchPlayerResponseDTO getWinner() {
        return winner;
    }

    public void setWinner(MatchPlayerResponseDTO winner) {
        this.winner = winner;
    }
}
