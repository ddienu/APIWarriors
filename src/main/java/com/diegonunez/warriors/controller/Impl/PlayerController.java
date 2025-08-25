package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IPlayerController;
import com.diegonunez.warriors.dto.Request.PlayerRequestDTO;
import com.diegonunez.warriors.dto.Response.PageResponse;
import com.diegonunez.warriors.dto.Response.PlayerResponseDTO;
import com.diegonunez.warriors.dto.Response.WarriorResponseDTO;
import com.diegonunez.warriors.service.Impl.PlayerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    //@PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<ApiResponse<PageResponse<PlayerResponseDTO>>> getAllPlayers(
            @PageableDefault(page = 0, size = 10)
            Pageable pageable,
            @RequestParam(required = false) String nickname) {

        Page<PlayerResponseDTO> serviceResponse;
        PageResponse<PlayerResponseDTO> pageResponse;

        if(nickname == null || nickname.isEmpty()){
            serviceResponse = playerService.findAllPlayers(pageable);
        }else{
            serviceResponse = playerService.findByNickname(nickname, pageable);
        }

        pageResponse =  new PageResponse<>(serviceResponse);


        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Players retrieved successfully",
                        pageResponse
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

    @GetMapping(path = "/points")
    @Override
    public ResponseEntity<ApiResponse<PageResponse<PlayerResponseDTO>>> getPlayersOrderByPoints(
            @PageableDefault(page = 0, size = 15) Pageable pageable) {
        Page<PlayerResponseDTO> serviceResponse = playerService.findPlayersOrderByPoints(pageable);
        PageResponse<PlayerResponseDTO> pageResponse = new PageResponse<>(serviceResponse);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Players order by points retrieved successfully",
                        pageResponse
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
