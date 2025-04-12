package com.diegonunez.warriors.dto.Request;

public class WarriorTypeUpdateDTO {
    private Integer typeWarriorId;

    public WarriorTypeUpdateDTO(Integer typeWarriorId) {
        this.typeWarriorId = typeWarriorId;
    }

    public Integer getTypeWarriorId() {
        return typeWarriorId;
    }

    public void setTypeWarriorId(Integer typeWarriorId) {
        this.typeWarriorId = typeWarriorId;
    }
}
