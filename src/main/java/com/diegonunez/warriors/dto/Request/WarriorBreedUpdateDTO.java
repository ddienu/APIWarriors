package com.diegonunez.warriors.dto.Request;

public class WarriorBreedUpdateDTO {

    private Integer breedId;

    public WarriorBreedUpdateDTO(Integer breedId){
        this.breedId = breedId;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }
}
