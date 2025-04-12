package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.TypeWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.TypeWarriorResponseDTO;
import com.diegonunez.warriors.entity.TypeWarrior;

import java.util.List;

public interface ITypeWarriorService {
    List<TypeWarriorResponseDTO> getAllTypeWarrior();
    TypeWarriorResponseDTO createTypeWarrior(TypeWarriorRequestDTO typeWarrior);

    TypeWarriorResponseDTO updateTypeWarrior(Integer typeWarriorId, TypeWarriorRequestDTO typeWarriorUpdated);
    boolean deleteTypeWarrior(Integer typeWarriorId);

}
