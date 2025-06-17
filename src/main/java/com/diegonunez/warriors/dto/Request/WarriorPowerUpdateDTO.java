package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WarriorPowerUpdateDTO {
    @NotNull(message = "Power's list cannot be null")
    @Size(max = 5, min = 5, message="The warrior must have five powers")
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
