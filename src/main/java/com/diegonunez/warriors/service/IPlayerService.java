package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Request.PlayerRequestDTO;
import com.diegonunez.warriors.dto.Response.PlayerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IPlayerService {
    PlayerResponseDTO findPlayerById(Integer playerId);
    Page<PlayerResponseDTO> findAllPlayers(Pageable pageable);
    Page<PlayerResponseDTO> findByNickname(String nickname, Pageable pageable);
    PlayerResponseDTO findPlayerByUserId(Integer userId);
    PlayerResponseDTO createPlayer(PlayerRequestDTO newPlayer);
    PlayerResponseDTO updatePlayer(Integer playerId, PlayerRequestDTO playerUpdated);
    Boolean deletePlayer(Integer playerId);
}
