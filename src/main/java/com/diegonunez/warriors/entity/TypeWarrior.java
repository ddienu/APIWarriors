package com.diegonunez.warriors.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypeWarrior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeWarriorId;
    private String typeWarriorName;
    private String typeWarriorDescription;

    //No args constructor
    public TypeWarrior(){}

    //Full args constructor
    public TypeWarrior(Integer typeWarriorId, String typeWarriorName, String typeWarriorDescription){
        this.typeWarriorId = typeWarriorId;
        this.typeWarriorName = typeWarriorName;
        this.typeWarriorDescription = typeWarriorDescription;
    }

    //Getters and setters
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

    @Override
    public String toString() {
        return "TypeWarrior{" +
                "typeWarriorId:" + typeWarriorId +
                ", typeWarriorName:'" + typeWarriorName + '\'' +
                ", typeWarriorDescription:'" + typeWarriorDescription + '\'' +
                '}';
    }
}
