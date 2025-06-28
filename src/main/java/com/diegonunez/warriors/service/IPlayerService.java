package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.PlayerRequestDTO;
import com.diegonunez.warriors.dto.Response.PlayerResponseDTO;

import java.util.List;

public interface IPlayerService {
    PlayerResponseDTO findPlayerById(Integer playerId);
    List<PlayerResponseDTO> findAllPlayers();
    PlayerResponseDTO createPlayer(PlayerRequestDTO newPlayer);
    PlayerResponseDTO updatePlayer(Integer playerId, PlayerRequestDTO playerUpdated);
    Boolean deletePlayer(Integer playerId);
}
