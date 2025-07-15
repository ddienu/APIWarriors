package com.diegonunez.warriors.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String code;
    private Boolean isActive;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @ManyToMany
    @JoinTable(
            name = "match_players",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;
    private Integer maxPlayers;
    private Integer actualPlayers;
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;


    public Match(){}
    public Match(Integer id, String name, String code, Boolean isActive, LocalDateTime createdAt, User createdBy, List<Player> players, Integer maxPlayers, Player winner, Integer actualPlayers) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.players = players;
        this.maxPlayers = maxPlayers;
        this.winner = winner;
        this.actualPlayers = actualPlayers;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Integer getActualPlayers() {
        return actualPlayers;
    }
    public void setActualPlayers(Integer actualPlayers) {
        this.actualPlayers = actualPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
