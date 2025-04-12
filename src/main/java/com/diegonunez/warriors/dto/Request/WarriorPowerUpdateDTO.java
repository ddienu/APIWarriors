package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class WarriorPowerUpdateDTO {
    @NotNull(message = "Power's list cannot be null")
    private List<Integer> powerIds;

    public WarriorPowerUpdateDTO(List<Integer> powerIds){
        this.powerIds = powerIds;
    }

    public List<Integer> getPowerIds() {
        return powerIds;
    }

    public void setPowerIds(List<Integer> powerIds) {
        this.powerIds = powerIds;
    }
}
