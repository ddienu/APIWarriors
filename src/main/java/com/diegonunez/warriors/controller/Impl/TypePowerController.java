package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.ITypePowerController;
import com.diegonunez.warriors.dto.Request.TypePowerRequestDTO;
import com.diegonunez.warriors.dto.Response.TypePowerResponseDTO;
import com.diegonunez.warriors.service.Impl.TypePowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "v1/typePower")
public class TypePowerController implements ITypePowerController {

    private final TypePowerService typePowerService;

    public TypePowerController (TypePowerService typePowerService){
        this.typePowerService = typePowerService;
    }


    @GetMapping
    @Override
    public ResponseEntity<ApiResponse<List<TypePowerResponseDTO>>> getAllPowers() {
        List<TypePowerResponseDTO> result = typePowerService.getAllPowers();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Powers retrieved successfully",
                        result
                )
        );
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponse<TypePowerResponseDTO>> createPower(TypePowerRequestDTO typePowerRequest) {
        TypePowerResponseDTO result = typePowerService.createPower(typePowerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Power created successfully",
                        result
                )
        );
    }
    @PutMapping(path = "/{powerId}")
    @Override
    public ResponseEntity<ApiResponse<TypePowerResponseDTO>> updatePower(Integer powerId, TypePowerRequestDTO typePowerUpdate) {
        TypePowerResponseDTO result = typePowerService.updatePower(powerId, typePowerUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Power with ID: "+powerId+ " updated",
                        result
                )
        );
    }

    @DeleteMapping(path = "/{powerId}")
    @Override
    public ResponseEntity<ApiResponse<Boolean>> deletePower(Integer powerId) {
        boolean response = typePowerService.deletePower(powerId);

        if( !response ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(
                            "Error deleting power",
                            Boolean.FALSE
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Power with ID: "+powerId+" deleted successfully",
                        Boolean.TRUE
                )
        );
    }
}

