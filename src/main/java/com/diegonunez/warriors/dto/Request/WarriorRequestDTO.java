package com.diegonunez.warriors.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WarriorRequestDTO {

    @NotNull(message = "Warrior's name cannot be null")
    @NotBlank(message = "Warrior's name cannot be empty")
    private String warriorName;
    @NotNull(message = "Warrior's life cannot be null")
    private Double warriorLife;
    @NotNull(message = "Warrior's energy cannot be null")
    private Double warriorEnergy;
    @JsonProperty(value = "warriorType")
    @NotNull(message = "Warrior type cannot be null")
    private Integer typeOfWarrior;
    @NotNull(message = "Warrior's powers cannot be null")
    @Size(min= 5, max = 5, message="The warrior must have 5 powers")
    private List<Integer> powers;
    @NotNull(message = "Warrior's breed cannot be null")
    private Integer breedWarrior;

    public WarriorRequestDTO(String warriorName, Double warriorLife, Double warriorEnergy, Integer typeOfWarrior, List<Integer> powers, Integer breedWarrior) {
        this.warriorName = warriorName;
        this.warriorLife = warriorLife;
        this.warriorEnergy = warriorEnergy;
        this.typeOfWarrior = typeOfWarrior;
        this.powers = powers;
        this.breedWarrior = breedWarrior;
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

    public Integer getTypeOfWarrior() {
        return typeOfWarrior;
    }

    public void setTypeOfWarrior(Integer typeOfWarrior) {
        this.typeOfWarrior = typeOfWarrior;
    }

    public List<Integer> getPowers() {
        return powers;
    }

    public void setPowers(List<Integer> powers) {
        this.powers = powers;
    }

    public Integer getBreedWarrior() {
        return breedWarrior;
    }

    public void setBreedWarrior(Integer breedWarrior) {
        this.breedWarrior = breedWarrior;
    }
}
