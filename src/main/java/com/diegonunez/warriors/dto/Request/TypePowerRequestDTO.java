package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TypePowerRequestDTO {

    @NotNull(message = "Power's name cannot be null")
    @NotBlank(message = "Power's name cannot be blank")
    private String powerName;
    @NotNull(message = "Power's damage cannot be null")
    private Integer powerDamage;
    @NotNull(message = "Power's energy consumed cannot be null")
    private Integer powerEnergyConsumed;
    @NotNull(message = "Power's description cannot be null")
    @NotBlank(message = "Power's description cannot be blank")
    private String powerDescription;

    public TypePowerRequestDTO(String powerName, Integer powerDamage, Integer powerEnergyConsumed,
                               String powerDescription){
        this.powerName = powerName;
        this.powerDamage = powerDamage;
        this.powerEnergyConsumed = powerEnergyConsumed;
        this.powerDescription = powerDescription;
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
}
