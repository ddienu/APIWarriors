    package com.diegonunez.warriors.controller.Impl;

    import com.diegonunez.warriors.common.ApiResponse;
    import com.diegonunez.warriors.controller.IBreedWarriorController;
    import com.diegonunez.warriors.dto.Request.BreedWarriorRequestDTO;
    import com.diegonunez.warriors.dto.Response.BreedWarriorResponseDTO;
    import com.diegonunez.warriors.entity.BreedWarrior;
    import com.diegonunez.warriors.service.Impl.BreedWarriorServiceImpl;
    import jakarta.validation.Valid;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "v1/breedWarrior")
    public class BreedWarriorController implements IBreedWarriorController {

        private final BreedWarriorServiceImpl breedWarriorService;

        public BreedWarriorController( BreedWarriorServiceImpl breedWarriorService ){
            this.breedWarriorService = breedWarriorService;
        }

        @Override
        public ResponseEntity<ApiResponse<List<BreedWarriorResponseDTO>>> getAllBreedWarrior() {
            List<BreedWarriorResponseDTO> response = breedWarriorService.getAllBreedWarrior();

            return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                    "Breed retrieved successfully",
                        response
                        )
                );
        }

        @PostMapping
        @Override
        public ResponseEntity<ApiResponse<BreedWarriorResponseDTO>> createBreedWarrior(@Valid @RequestBody BreedWarriorRequestDTO breedWarrior) {
            BreedWarriorResponseDTO response = breedWarriorService.createBreedWarrior(breedWarrior);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(
                            "Breed created successfully",
                            response
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<BreedWarriorResponseDTO>> updateBreedWarrior(Integer breedId, BreedWarriorRequestDTO breedWarriorUpdate) {
            BreedWarriorResponseDTO response = breedWarriorService.updateBreedWarrior(breedId, breedWarriorUpdate);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            "Breed with ID: "+breedId+" updated successfully",
                            response
                    )
            );
        }

        @Override
        public ResponseEntity<ApiResponse<Boolean>> deleteBreedWarrior(Integer breedId) {
            boolean response = breedWarriorService.deleteBreedWarrior(breedId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ApiResponse<>(
                            "Breed with ID: "+breedId+" deleted successfully",
                            response
                    )
            );
        }
    }
