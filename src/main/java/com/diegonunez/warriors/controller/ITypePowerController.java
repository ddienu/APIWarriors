package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.TypePowerRequestDTO;
import com.diegonunez.warriors.dto.Response.TypePowerResponseDTO;
import com.diegonunez.warriors.entity.TypePower;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ITypePowerController {
    ResponseEntity<ApiResponse<List<TypePowerResponseDTO>>> getAllPowers();
    ResponseEntity<ApiResponse<TypePowerResponseDTO>> createPower (@Valid @RequestBody TypePowerRequestDTO typePowerRequest);
    ResponseEntity<ApiResponse<TypePowerResponseDTO>> updatePower(@PathVariable Integer powerId, @Valid @RequestBody TypePowerRequestDTO typePowerUpdate);
    ResponseEntity<ApiResponse<Boolean>> deletePower (@PathVariable Integer powerId);

}
