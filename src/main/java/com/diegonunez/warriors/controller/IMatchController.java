package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.request.MatchRequestWinnerDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;
import com.diegonunez.warriors.dto.Request.JoinMatchRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMatchController {

    ResponseEntity<ApiResponse<MatchResponseDTO>> findMatchById(Integer matchId);
    ResponseEntity<ApiResponse<List<MatchResponseDTO>>> findMatches();
    ResponseEntity<ApiResponse<MatchResponseDTO>> createMatch(MatchRequestDTO newMatch);
    ResponseEntity<ApiResponse<MatchResponseDTO>> simulateBattle(MatchRequestWinnerDTO matchPayload);
    ResponseEntity<ApiResponse<Boolean>> deleteMatch(Integer matchId);

    ResponseEntity<ApiResponse<String>> joinMatch(JoinMatchRequestDTO joinMatchPayload);

}
