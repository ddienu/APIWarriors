package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMatchController {

    ResponseEntity<ApiResponse<MatchResponseDTO>> findMatchById(Integer matchId);
    ResponseEntity<ApiResponse<List<MatchResponseDTO>>> findMatches();
    ResponseEntity<ApiResponse<MatchResponseDTO>> createMatch(MatchRequestDTO newMatch);
    ResponseEntity<ApiResponse<MatchResponseDTO>> setMatchWinner(Integer matchId, Integer playerId);
    ResponseEntity<ApiResponse<Boolean>> deleteMatch(Integer matchId);

}
