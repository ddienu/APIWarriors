package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IPlayerController;
import com.diegonunez.warriors.dto.Request.PlayerRequestDTO;
import com.diegonunez.warriors.dto.Response.PlayerResponseDTO;
import com.diegonunez.warriors.service.Impl.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/player")
public class PlayerController implements IPlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping(path = "/{playerId}")
    @Override
    public ResponseEntity<ApiResponse<PlayerResponseDTO>> getPlayerById(@PathVariable Integer playerId) {
        PlayerResponseDTO serviceResponse = playerService.findPlayerById(playerId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Player with ID: "+playerId+" retrieved successfully",
                        serviceResponse
                )
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ApiResponse<List<PlayerResponseDTO>>> getAllPlayers() {
        List<PlayerResponseDTO> serviceResponse = playerService.findAllPlayers();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Players retrieved successfully",
                        serviceResponse
                )
        );
    }

    @GetMapping(path = "/user/{userId}")
    @Override
    public ResponseEntity<ApiResponse<PlayerResponseDTO>> getPlayerByUserId(@PathVariable Integer userId) {
        PlayerResponseDTO serviceResponse = playerService.findPlayerByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Player of user with ID: "+userId+" retrieved successfully",
                        serviceResponse
                )
        );
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponse<PlayerResponseDTO>> createPlayer(@Valid @RequestBody PlayerRequestDTO newPlayer) {
        PlayerResponseDTO serviceResponse = playerService.createPlayer(newPlayer);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Player created successfully",
                        serviceResponse
                )
        );
    }

    @PutMapping(path = "/{playerId}")
    @Override
    public ResponseEntity<ApiResponse<PlayerResponseDTO>> updatePlayer(@PathVariable Integer playerId,@Valid @RequestBody PlayerRequestDTO playerUpdated) {
        PlayerResponseDTO serviceResponse = playerService.updatePlayer(playerId, playerUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Player with ID: "+playerId+" updated successfully",
                        serviceResponse
                )
        );
    }

    @DeleteMapping(path = "/{playerId}")
    @Override
    public ResponseEntity<ApiResponse<Boolean>> deletePlayer(@PathVariable Integer playerId) {
        Boolean serviceResponse = playerService.deletePlayer(playerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Player with ID: "+playerId+" deleted successfully",
                        serviceResponse
                )
        );
    }
}
