package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.BreedWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.BreedWarriorResponseDTO;
import com.diegonunez.warriors.entity.BreedWarrior;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IBreedWarriorController {
    @GetMapping
    ResponseEntity<ApiResponse<List<BreedWarriorResponseDTO>>>  getAllBreedWarrior();
    @PostMapping
    ResponseEntity<ApiResponse<BreedWarriorResponseDTO>> createBreedWarrior(@Valid @RequestBody BreedWarriorRequestDTO breedWarrior);
    @PutMapping(path = "/{breedId}")
    ResponseEntity<ApiResponse<BreedWarriorResponseDTO>> updateBreedWarrior(@PathVariable Integer breedId, @Valid @RequestBody BreedWarriorRequestDTO breedWarriorUpdate);
    @DeleteMapping(path = "/{breedId}")
    ResponseEntity<ApiResponse<Boolean>> deleteBreedWarrior(@PathVariable Integer breedId);
}
