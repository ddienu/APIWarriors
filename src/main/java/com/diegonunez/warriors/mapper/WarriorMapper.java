package com.diegonunez.warriors.mapper;

import com.diegonunez.warriors.dto.Response.BreedWarriorResponseDTO;
import com.diegonunez.warriors.dto.Response.TypePowerResponseDTO;
import com.diegonunez.warriors.dto.Response.TypeWarriorResponseDTO;
import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
import com.diegonunez.warriors.entity.Warrior;
public final class WarriorMapper {

    private WarriorMapper(){
        throw new UnsupportedOperationException("Utility class");
    }
    public static WarriorResponseDTO toDTO(Warrior warrior){
        return new WarriorResponseDTO(
                warrior.getWarriorId(),
                warrior.getWarriorName(),
                warrior.getWarriorLife(),
                warrior.getWarriorEnergy(),
                new TypeWarriorResponseDTO(
                        warrior.getTypeOfWarrior().getTypeWarriorId(),
                        warrior.getTypeOfWarrior().getTypeWarriorName(),
                        warrior.getTypeOfWarrior().getTypeWarriorDescription()
                ),
                warrior.getTypeOfPower().stream().map(
                    power -> new TypePowerResponseDTO(
                        power.getPowerId(),
                        power.getPowerName(),
                        power.getPowerDamage(),
                        power.getPowerEnergyConsumed(),
                        power.getPowerDescription()
                    )
                ).toList(),
                new BreedWarriorResponseDTO(
                        warrior.getBreedWarrior().getBreedId(),
                        warrior.getBreedWarrior().getBreedName(),
                        warrior.getBreedWarrior().getBreedName(),
                        warrior.getBreedWarrior().getBreedDescription()
                )
        );
    }
}
