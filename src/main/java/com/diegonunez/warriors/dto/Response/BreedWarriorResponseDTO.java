package com.diegonunez.warriors.dto.Response;

public class BreedWarriorResponseDTO {

    private Integer breedId;

    private String breedName;

    private String breedDescription;

    private String breedResistance;

    public BreedWarriorResponseDTO(Integer breedId, String breedName, String breedDescription, String breedResistance){
        this.breedId = breedId;
        this.breedName = breedName;
        this.breedDescription = breedDescription;
        this.breedResistance = breedResistance;
    }

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
}
