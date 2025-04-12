package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.TypePowerRequestDTO;
import com.diegonunez.warriors.dto.Response.TypePowerResponseDTO;
import com.diegonunez.warriors.entity.TypePower;

import java.util.List;

public interface ITypePowerService {
    List<TypePowerResponseDTO> getAllPowers();
    TypePowerResponseDTO createPower(TypePowerRequestDTO newPowerRequest);

    TypePowerResponseDTO updatePower(Integer powerId, TypePowerRequestDTO updatedPower);
    boolean deletePower(Integer powerId);

}
