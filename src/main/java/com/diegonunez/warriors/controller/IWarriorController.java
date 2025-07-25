package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.*;
import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IWarriorController {

    ResponseEntity<ApiResponse<Page<WarriorResponseDTO>>> getAllWarriors(Pageable pageable);
    @GetMapping(path = "/{warriorId}")
    ResponseEntity<ApiResponse<WarriorResponseDTO>> getWarriorById(@PathVariable Integer warriorId);
    ResponseEntity<ApiResponse<WarriorResponseDTO>> createWarrior(@Valid @RequestBody WarriorRequestDTO warrior);
    @PatchMapping(path = "/basics/{warriorId}")
    ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorBasics(@PathVariable Integer warriorId, @Valid @RequestBody WarriorBasicsUpdateDTO warriorUpdate);
    @PatchMapping(path = "/powers/{warriorId}")
    ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorPowers(@PathVariable Integer warriorId, @Valid @RequestBody WarriorPowerUpdateDTO powerIds);
    @PatchMapping(path = "/breed/{warriorId}")
    ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorBreed(@PathVariable Integer warriorId, @Valid @RequestBody WarriorBreedUpdateDTO warriorBreedUpdated);
    @PatchMapping(path = "/type/{warriorId}")
    ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorType(@PathVariable Integer warriorId, @Valid @RequestBody WarriorTypeUpdateDTO warriorTypeUpdated);
    @DeleteMapping(path = "/{warriorId}")
    ResponseEntity<ApiResponse<Boolean>> deleteWarrior(@PathVariable Integer warriorId);
}
