package com.diegonunez.warriors.service;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.*;
import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
import com.diegonunez.warriors.entity.BreedWarrior;
import com.diegonunez.warriors.entity.TypePower;
import com.diegonunez.warriors.entity.TypeWarrior;
import com.diegonunez.warriors.entity.Warrior;

import java.util.List;

public interface IWarriorService {
    List<WarriorResponseDTO> getAllWarriors();
    WarriorResponseDTO getWarriorById(Integer warriorId);
    WarriorResponseDTO createWarrior(WarriorRequestDTO warrior);
    WarriorResponseDTO updateWarriorBasics(Integer warriorId, WarriorBasicsUpdateDTO warriorBasicsUpdated);
    WarriorResponseDTO updateWarriorPowers(Integer warriorId, WarriorPowerUpdateDTO warriorPowerUpdated);
    WarriorResponseDTO updateWarriorBreed(Integer warriorId, WarriorBreedUpdateDTO warriorBreedUpdated);
    WarriorResponseDTO updateWarriorType(Integer warriorId, WarriorTypeUpdateDTO warriorTypeUpdated);
    boolean deleteWarrior(Integer warriorId);
}
