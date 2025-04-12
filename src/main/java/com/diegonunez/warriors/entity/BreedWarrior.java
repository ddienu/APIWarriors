package com.diegonunez.warriors.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BreedWarrior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer breedId;
    private String breedName;
    private String breedDescription;
    private String breedResistance;

    //No args constructor
    public BreedWarrior(){}

    //Full args constructor
    public BreedWarrior(String breedName, String breedDescription, String breedResistance){
        this.breedName = breedName;
        this.breedDescription = breedDescription;
        this.breedResistance = breedResistance;
    }

    //Getters and setters
    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedDescription() {
        return breedDescription;
    }

    public void setBreedDescription(String breedDescription) {
        this.breedDescription = breedDescription;
    }

    public String getBreedResistance() {
        return breedResistance;
    }

    public void setBreedResistance(String breedResistance) {
        this.breedResistance = breedResistance;
    }

    @Override
    public String toString() {
        return "BreedWarrior{" +
                "breedId=" + breedId +
                ", breedName:'" + breedName + '\'' +
                ", breedDescription:'" + breedDescription + '\'' +
                ", breedResistance:'" + breedResistance + '\'' +
                '}';
    }
}
