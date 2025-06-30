package com.diegonunez.warriors.controller.Impl;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.controller.IMatchController;
import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;
import com.diegonunez.warriors.service.Impl.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/match")
public class MatchController implements IMatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @GetMapping(path = "/{matchId}")
    @Override
    public ResponseEntity<ApiResponse<MatchResponseDTO>> findMatchById(@PathVariable Integer matchId) {
        MatchResponseDTO serviceResponse = matchService.getMatchById(matchId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Match with ID: "+matchId+" retrieved successfully",
                        serviceResponse
                )
        );
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponse<List<MatchResponseDTO>>> findMatches() {
        List<MatchResponseDTO> serviceResponse = matchService.getMatches();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Matches retrieved successfully",
                        serviceResponse
                )
        );
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponse<MatchResponseDTO>> createMatch(@Valid @RequestBody MatchRequestDTO newMatch) {
         MatchResponseDTO serviceResponse = matchService.createMatch(newMatch);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Match created successfully",
                        serviceResponse
                )
        );
    }

    @PutMapping(path = "/{matchId}/winner/{playerId}")
    @Override
    public ResponseEntity<ApiResponse<MatchResponseDTO>> setMatchWinner(@PathVariable Integer matchId, @PathVariable Integer playerId) {
        MatchResponseDTO serviceResponse = matchService.setMatchWinner(matchId, playerId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Match with ID: "+matchId+" updated successfully",
                        serviceResponse
                )
        );
    }

    @DeleteMapping(path = "/{matchId}")
    @Override
    public ResponseEntity<ApiResponse<Boolean>> deleteMatch(@PathVariable Integer matchId) {
        Boolean serviceResponse = matchService.deleteMatch(matchId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Match with ID: "+matchId+" deleted successfully",
                        serviceResponse
                )
        );
    }
}
