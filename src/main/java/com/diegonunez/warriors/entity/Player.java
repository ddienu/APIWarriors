package com.diegonunez.warriors.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    @Column(unique = true)
    private String nickname;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "player_warrior",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "warrior_id")
    )
    private List<Warrior> warriorsSelected;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    private Integer points;
    private Integer gamesWon;
    private Integer gamesLost;

    //No args constructor
    public Player(){}

    //Full args constructor
    public Player(Integer playerId, String nickname, List<Warrior> warriorsSelected, User user, Integer points,
                  Integer gamesWon, Integer gamesLost ){
        this.playerId = playerId;
        this.nickname = nickname;
        this.warriorsSelected = warriorsSelected;
        this.user = user;
        this.points = points;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    //Getters and Setter
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

    public List<Warrior> getWarriorsSelected() {
        return warriorsSelected;
    }

    public void setWarriorsSelected(List<Warrior> warriorsSelected) {
        this.warriorsSelected = warriorsSelected;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
