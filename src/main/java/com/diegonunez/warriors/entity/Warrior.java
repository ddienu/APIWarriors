package com.diegonunez.warriors.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Warrior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warriorId;
    @Column(unique = true)
    private String warriorName;
    private Double warriorLife;
    private Double warriorEnergy;
    @ManyToOne
    @JoinColumn(name = "type_warrior_id")
    private TypeWarrior typeOfWarrior;
    @ManyToMany
    @JoinTable(
            name = "warrior_powers",
            joinColumns = @JoinColumn(name = "warrior_id"),
            inverseJoinColumns = @JoinColumn(name = "power_id")
    )
    @JsonProperty("powers")
    private List<TypePower> powers;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private BreedWarrior breedWarrior;

    //No args constructor
    public Warrior(){}

    //Full args constructor
    public Warrior(Integer warriorId, String warriorName, Double warriorLife, Double warriorEnergy,
                   TypeWarrior typeOfWarrior, List<TypePower> powers, BreedWarrior breedWarrior){
        this.warriorId = warriorId;
        this.warriorName = warriorName;
        this.warriorLife = warriorLife;
        this.warriorEnergy = warriorEnergy;
        this.typeOfWarrior = typeOfWarrior;
        this.powers = powers;
        this.breedWarrior = breedWarrior;
    }

    //Getters and setters
    public Integer getWarriorId() {
        return warriorId;
    }

    public void setWarriorId(Integer warriorId) {
        this.warriorId = warriorId;
    }

    public String getWarriorName() {
        return warriorName;
    }

    public void setWarriorName(String warriorName) {
        this.warriorName = warriorName;
    }

    public Double getWarriorLife() {
        return warriorLife;
    }

    public void setWarriorLife(Double warriorLife) {
        this.warriorLife = warriorLife;
    }

    public Double getWarriorEnergy() {
        return warriorEnergy;
    }

    public void setWarriorEnergy(Double warriorEnergy) {
        this.warriorEnergy = warriorEnergy;
    }

    public TypeWarrior getTypeOfWarrior() {
        return typeOfWarrior;
    }

    public void setTypeOfWarrior(TypeWarrior typeOfWarrior) {
        this.typeOfWarrior = typeOfWarrior;
    }

    public List<TypePower> getTypeOfPower() {
        return powers;
    }

    public void setTypeOfPower(List<TypePower> typeOfPower) {
        this.powers = typeOfPower;
    }

    public BreedWarrior getBreedWarrior() {
        return breedWarrior;
    }

    public void setBreedWarrior(BreedWarrior breedWarrior) {
        this.breedWarrior = breedWarrior;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "warriorId:" + warriorId +
                ", warriorName:'" + warriorName + '\'' +
                ", warriorLife:" + warriorLife +
                ", warriorEnergy:" + warriorEnergy +
                ", typeOfWarrior:" + typeOfWarrior.toString() +
                ", powers:" + powers.toString() +
                ", breedWarrior:" + breedWarrior.toString() +
                '}';
    }
}
