package com.diegonunez.warriors.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TypePower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "power_id")
    private Integer powerId;
    private String powerName;
    private Integer powerDamage;
    private Integer powerEnergyConsumed;
    private String powerDescription;
    @ManyToMany(mappedBy = "powers")
    private List<Warrior> warrior;

    //No args constructor
    public TypePower(){}

    //Full args constructor
    public TypePower(Integer powerId, String powerName, Integer powerDamage, Integer powerEnergyConsumed,
                     String powerDescription, List<Warrior> warrior){
        this.powerId = powerId;
        this.powerName = powerName;
        this.powerDamage = powerDamage;
        this.powerEnergyConsumed = powerEnergyConsumed;
        this.powerDescription = powerDescription;
        this.warrior = warrior;
    }

    //Getters and setters
    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public Integer getPowerDamage() {
        return powerDamage;
    }

    public void setPowerDamage(Integer powerDamage) {
        this.powerDamage = powerDamage;
    }

    public Integer getPowerEnergyConsumed() {
        return powerEnergyConsumed;
    }

    public void setPowerEnergyConsumed(Integer powerEnergyConsumed) {
        this.powerEnergyConsumed = powerEnergyConsumed;
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription;
    }

    public List<Warrior> getWarrior() {
        return warrior;
    }

    public void setWarrior(List<Warrior> warrior) {
        this.warrior = warrior;
    }

    @Override
    public String toString() {
        return "TypePower{" +
                "powerId=" + powerId +
                ", powerName:'" + powerName + '\'' +
                ", powerDamage:" + powerDamage +
                ", powerEnergyConsumed:" + powerEnergyConsumed +
                ", powerDescription:'" + powerDescription + '\'' +
                '}';
    }
}
