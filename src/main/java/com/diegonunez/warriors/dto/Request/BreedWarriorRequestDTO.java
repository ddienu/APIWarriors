package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BreedWarriorRequestDTO {
    @NotNull(message = "Breed's name cannot be null")
    @NotBlank(message = "Breed's name cannot be blank")
    private String breedName;
    @NotNull(message = "Breed's description cannot be null")
    @NotBlank(message = "Breed's description cannot be blank")
    private String breedDescription;
    @NotNull(message = "Breed's resistance cannot be null")
    @NotBlank(message = "Breed's resistance cannot be blank")
    private String breedResistance;

    public BreedWarriorRequestDTO(String breedName, String breedDescription, String breedResistance){
        this.breedName = breedName;
        this.breedDescription = breedDescription;
        this.breedResistance = breedResistance;
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
}
