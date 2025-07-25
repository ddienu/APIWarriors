package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.*;
import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWarriorService {
    Page<WarriorResponseDTO> getAllWarriors(Pageable pageable);
    WarriorResponseDTO getWarriorById(Integer warriorId);
    WarriorResponseDTO createWarrior(WarriorRequestDTO warrior);
    WarriorResponseDTO updateWarriorBasics(Integer warriorId, WarriorBasicsUpdateDTO warriorBasicsUpdated);
    WarriorResponseDTO updateWarriorPowers(Integer warriorId, WarriorPowerUpdateDTO warriorPowerUpdated);
    WarriorResponseDTO updateWarriorBreed(Integer warriorId, WarriorBreedUpdateDTO warriorBreedUpdated);
    WarriorResponseDTO updateWarriorType(Integer warriorId, WarriorTypeUpdateDTO warriorTypeUpdated);
    boolean deleteWarrior(Integer warriorId);
}
