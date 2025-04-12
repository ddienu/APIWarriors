package com.diegonunez.warriors.dto.Response;

public class TypeWarriorResponseDTO {

    private Integer typeWarriorId;
    private String typeWarriorName;
    private String typeWarriorDescription;

    public TypeWarriorResponseDTO(Integer typeWarriorId, String typeWarriorName, String typeWarriorDescription){
        this.typeWarriorId = typeWarriorId;
        this.typeWarriorName = typeWarriorName;
        this.typeWarriorDescription = typeWarriorDescription;
    }

    public Integer getTypeWarriorId() {
        return typeWarriorId;
    }

    public void setTypeWarriorId(Integer typeWarriorId) {
        this.typeWarriorId = typeWarriorId;
    }

    public String getTypeWarriorName() {
        return typeWarriorName;
    }

    public void setTypeWarriorName(String typeWarriorName) {
        this.typeWarriorName = typeWarriorName;
    }

    public String getTypeWarriorDescription() {
        return typeWarriorDescription;
    }

    public void setTypeWarriorDescription(String typeWarriorDescription) {
        this.typeWarriorDescription = typeWarriorDescription;
    }
}
