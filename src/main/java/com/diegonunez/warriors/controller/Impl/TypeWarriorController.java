package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.ITypeWarriorController;
import com.diegonunez.warriors.dto.Request.TypeWarriorRequestDTO;
import com.diegonunez.warriors.dto.Response.TypeWarriorResponseDTO;
import com.diegonunez.warriors.service.Impl.TypeWarriorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "v1/typeWarrior")
public class TypeWarriorController implements ITypeWarriorController {

    private final TypeWarriorService typeWarriorService;

    public TypeWarriorController( TypeWarriorService typeWarriorService ){
        this.typeWarriorService = typeWarriorService;
    }

    @Override
    public ResponseEntity<ApiResponse<List<TypeWarriorResponseDTO>>> getAllTypeWarriors() {
        List<TypeWarriorResponseDTO> response = typeWarriorService.getAllTypeWarrior();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Type of warriors retrieved successfully",
                        response
                )
        );
    }

    @Override
    public ResponseEntity<ApiResponse<TypeWarriorResponseDTO>> createTypeWarrior(TypeWarriorRequestDTO typeWarrior) {
        TypeWarriorResponseDTO response = typeWarriorService.createTypeWarrior(typeWarrior);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Type of warrior created successfully",
                        response
                )
        );
    }

    @Override
    public ResponseEntity<ApiResponse<TypeWarriorResponseDTO>> updateTypeWarrior(Integer typeWarriorId, TypeWarriorRequestDTO typePowerUpdated) {
        TypeWarriorResponseDTO response = typeWarriorService.updateTypeWarrior(typeWarriorId, typePowerUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Type of warrior with ID: "+typeWarriorId+" updated successfully",
                        response
                )
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Boolean>> deleteTypeWarrior(Integer typeWarriorId) {
        boolean response = typeWarriorService.deleteTypeWarrior(typeWarriorId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Type of warrior with ID: "+typeWarriorId+" deleted successfully",
                        response
                )
        );
    }
}
