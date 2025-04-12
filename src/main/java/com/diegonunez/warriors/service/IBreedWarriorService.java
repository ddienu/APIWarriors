package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.BreedWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.BreedWarriorResponseDTO;
import com.diegonunez.warriors.entity.BreedWarrior;

import java.util.List;

public interface IBreedWarriorService {
    List<BreedWarriorResponseDTO> getAllBreedWarrior();
    BreedWarriorResponseDTO createBreedWarrior(BreedWarriorRequestDTO breedWarrior);

    BreedWarriorResponseDTO updateBreedWarrior(Integer breedId, BreedWarriorRequestDTO breedWarriorUpdate);

    boolean deleteBreedWarrior(Integer breedId);

}
