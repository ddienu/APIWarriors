package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.TypePowerRequestDTO;
import com.diegonunez.warriors.dto.Request.TypeWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.TypeWarriorResponseDTO;
import com.diegonunez.warriors.entity.TypeWarrior;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ITypeWarriorController {

    @GetMapping
    ResponseEntity<ApiResponse<List<TypeWarriorResponseDTO>>> getAllTypeWarriors();
    @PostMapping
    ResponseEntity<ApiResponse<TypeWarriorResponseDTO>> createTypeWarrior(@Valid @RequestBody TypeWarriorRequestDTO typeWarrior);
    @PutMapping(path = "/{typeWarriorId}")
    ResponseEntity<ApiResponse<TypeWarriorResponseDTO>> updateTypeWarrior(@PathVariable Integer typeWarriorId, @Valid @RequestBody TypeWarriorRequestDTO typePowerUpdated);
    @DeleteMapping(path = "/{typeWarriorId}")
    ResponseEntity<ApiResponse<Boolean>> deleteTypeWarrior(@PathVariable Integer typeWarriorId);

}
