package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WarriorBasicsUpdateDTO {

    @NotNull(message = "Warrior's name cannot be null")
    @NotBlank(message = "Warrior's name cannot be empty")
    private String warriorName;
    @NotNull(message = "Warrior's life cannot be null")
    private double warriorLife;
    @NotNull(message = "Warrior's energy cannot be null")
    private double warriorEnergy;

    public WarriorBasicsUpdateDTO(String warriorName, double warriorLife, double warriorEnergy){
        this.warriorName = warriorName;
        this.warriorLife = warriorLife;
        this.warriorEnergy = warriorEnergy;
    }

    public String getWarriorName() {
        return warriorName;
    }

    public void setWarriorName(String warriorName) {
        this.warriorName = warriorName;
    }

    public double getWarriorLife() {
        return warriorLife;
    }

    public void setWarriorLife(double warriorLife) {
        this.warriorLife = warriorLife;
    }

    public double getWarriorEnergy() {
        return warriorEnergy;
    }

    public void setWarriorEnergy(double warriorEnergy) {
        this.warriorEnergy = warriorEnergy;
    }
}
