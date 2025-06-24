package com.diegonunez.warriors.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
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

    //No args constructor
    public Player(){}

    //Full args constructor
    public Player(Integer playerId, String nickname, List<Warrior> warriorsSelected, User user){
        this.playerId = playerId;
        this.nickname = nickname;
        this.warriorsSelected = warriorsSelected;
        this.user = user;
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
}
