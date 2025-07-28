    package com.diegonunez.warriors.controller.Impl;

    import com.diegonunez.warriors.controller.IWarriorController;
    import com.diegonunez.warriors.common.ApiResponse;
    import com.diegonunez.warriors.dto.Request.*;
    import com.diegonunez.warriors.dto.Response.PageResponse;
    import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
    import com.diegonunez.warriors.service.Impl.WarriorService;
    import jakarta.validation.Valid;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.web.PageableDefault;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/v1/warrior")
    public class WarriorController implements IWarriorController {

        private final WarriorService warriorService;

        public WarriorController ( WarriorService warriorService){
            this.warriorService = warriorService;
        }
        @Override
        @GetMapping
        public ResponseEntity<ApiResponse<PageResponse<WarriorResponseDTO>>> getAllWarriors(
                @PageableDefault(page = 0, size = 8) Pageable pageable) {
            Page<WarriorResponseDTO> response = warriorService.getAllWarriors(pageable);
            PageResponse<WarriorResponseDTO> pageResponse = new PageResponse<>(response);


            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Warriors retrieved successfully",
                            pageResponse
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<WarriorResponseDTO>> getWarriorById(Integer warriorId) {
            WarriorResponseDTO result = warriorService.getWarriorById(warriorId);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Warrior with ID: "+warriorId+" retrieved successfully",
                            result
                    )
            );

        }

        @PostMapping
        @Override
        public ResponseEntity<ApiResponse<WarriorResponseDTO>> createWarrior(@Valid @RequestBody WarriorRequestDTO warrior) {
            WarriorResponseDTO result = warriorService.createWarrior(warrior);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(
                            "Warrior created successfully",
                            result
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorBasics(Integer warriorId, WarriorBasicsUpdateDTO warriorUpdate) {
            WarriorResponseDTO result = warriorService.updateWarriorBasics(warriorId, warriorUpdate);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Warrior with ID: "+warriorId+" updated successfully",
                            result
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorPowers(Integer warriorId, WarriorPowerUpdateDTO powerIds) {
            WarriorResponseDTO result = warriorService.updateWarriorPowers(warriorId, powerIds);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Powers of warrior with ID: "+warriorId+" updated successfully",
                            result
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorBreed(Integer warriorId, WarriorBreedUpdateDTO warriorBreedUpdated) {
            WarriorResponseDTO result = warriorService.updateWarriorBreed(warriorId, warriorBreedUpdated);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Breed of warrior with ID: "+warriorId+" updated successfully",
                            result
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<WarriorResponseDTO>> updateWarriorType(Integer warriorId, WarriorTypeUpdateDTO warriorTypeUpdated) {
            WarriorResponseDTO result = warriorService.updateWarriorType(warriorId, warriorTypeUpdated);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Type of warrior with ID: "+warriorId+" updated successfully",
                            result
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<Boolean>> deleteWarrior(Integer warriorId) {
            boolean result = warriorService.deleteWarrior(warriorId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ApiResponse<>(
                            "Warrior with ID: "+warriorId+" deleted successfully",
                            result
                    )
            );
        }

    }
