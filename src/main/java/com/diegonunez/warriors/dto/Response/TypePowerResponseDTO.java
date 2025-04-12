package com.diegonunez.warriors.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"powerId", "powerName", "powerDamage", "powerEnergyConsumed", "powerDescription"})
public class TypePowerResponseDTO {

    @JsonProperty("powerId")
    private Integer powerId;
    @JsonProperty("powerName")
    private String powerName;
    @JsonProperty("powerDamage")
    private Integer powerDamage;
    @JsonProperty("powerEnergyConsumed")
    private Integer powerEnergyConsumed;
    @JsonProperty("powerDescription")
    private String powerDescription;

    public TypePowerResponseDTO(Integer typePowerId, String powerName, Integer powerDamage, Integer powerEnergyConsumed,
                               String powerDescription){
        this.powerId = typePowerId;
        this.powerName = powerName;
        this.powerDamage = powerDamage;
        this.powerEnergyConsumed = powerEnergyConsumed;
        this.powerDescription = powerDescription;
    }

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
}
