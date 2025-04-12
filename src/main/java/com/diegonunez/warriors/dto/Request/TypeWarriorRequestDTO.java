package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TypeWarriorRequestDTO {

    @NotNull(message = "Type warrior name cannot be null")
    @NotBlank(message = "Type warrior name cannot be blank")
    private String typeWarriorName;
    @NotNull(message = "Type warrior description cannot be null")
    @NotBlank(message = "Type warrior description cannot be blank")
    private String typeWarriorDescription;

    public TypeWarriorRequestDTO (String typeWarriorName, String typeWarriorDescription){
        this.typeWarriorName = typeWarriorName;
        this.typeWarriorDescription = typeWarriorDescription;
    }

    public String getTypeWarriorName(){
        return typeWarriorName;
    }

    public void setTypeWarriorName(String typeWarriorName){
        this.typeWarriorName = typeWarriorName;
    }

    public String getTypeWarriorDescription() {
        return typeWarriorDescription;
    }

    public void setTypeWarriorDescription(String typeWarriorDescription) {
        this.typeWarriorDescription = typeWarriorDescription;
    }
}
