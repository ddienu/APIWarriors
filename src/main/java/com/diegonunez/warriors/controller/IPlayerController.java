package com.diegonunez.warriors.controller;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.dto.Request.PlayerRequestDTO;
import com.diegonunez.warriors.dto.Response.PageResponse;
import com.diegonunez.warriors.dto.Response.PlayerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPlayerController {
    ResponseEntity<ApiResponse<PlayerResponseDTO>> getPlayerById(Integer playerId);
    ResponseEntity<ApiResponse<PageResponse<PlayerResponseDTO>>> getAllPlayers(Pageable pageable, String nickname);
    ResponseEntity<ApiResponse<PlayerResponseDTO>> getPlayerByUserId(Integer userId);
    ResponseEntity<ApiResponse<PageResponse<PlayerResponseDTO>>> getPlayersOrderByPoints(Pageable pageable);
    ResponseEntity<ApiResponse<PlayerResponseDTO>> createPlayer(PlayerRequestDTO newPlayer);
    ResponseEntity<ApiResponse<PlayerResponseDTO>> updatePlayer(Integer playerId, PlayerRequestDTO playerUpdated);
    ResponseEntity<ApiResponse<Boolean>> deletePlayer(Integer playerId);

}
