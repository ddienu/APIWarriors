package com.diegonunez.warriors.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WarriorResponseDTO {
    private Integer warriorId;
    private String warriorName;
    private Double warriorLife;
    private Double warriorEnergy;
    @JsonProperty(value = "warriorType")
    private TypeWarriorResponseDTO typeOfWarrior;
    private List<TypePowerResponseDTO> powers;
    @JsonProperty(value = "warriorBreed")
    private BreedWarriorResponseDTO breedWarrior;

    public WarriorResponseDTO(Integer warriorId, String warriorName, Double warriorLife, Double warriorEnergy, TypeWarriorResponseDTO typeOfWarrior,
                              List<TypePowerResponseDTO> powers, BreedWarriorResponseDTO breedWarrior){
        this.warriorId = warriorId;
        this.warriorName = warriorName;
        this.warriorLife = warriorLife;
        this.warriorEnergy = warriorEnergy;
        this.typeOfWarrior = typeOfWarrior;
        this.powers = powers;
        this.breedWarrior = breedWarrior;
    }

    public WarriorResponseDTO(String warriorName, Double warriorLife, Double warriorEnergy, TypeWarriorResponseDTO typeOfWarrior,
                              List<TypePowerResponseDTO> powers, BreedWarriorResponseDTO breedWarriorName){
        this.warriorName = warriorName;
        this.warriorLife = warriorLife;
        this.warriorEnergy = warriorEnergy;
        this.typeOfWarrior = typeOfWarrior;
        this.powers = powers;
        this.breedWarrior = breedWarriorName;
    }

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

    public TypeWarriorResponseDTO getTypeOfWarrior() {
        return typeOfWarrior;
    }

    public void setTypeOfWarrior(TypeWarriorResponseDTO typeOfWarrior) {
        this.typeOfWarrior = typeOfWarrior;
    }

    public List<TypePowerResponseDTO> getPowers() {
        return powers;
    }

    public void setPowers(List<TypePowerResponseDTO> powers) {
        this.powers = powers;
    }

    public BreedWarriorResponseDTO getBreedWarrior() {
        return breedWarrior;
    }

    public void setBreedWarrior(BreedWarriorResponseDTO breedWarrior) {
        this.breedWarrior = breedWarrior;
    }
}
