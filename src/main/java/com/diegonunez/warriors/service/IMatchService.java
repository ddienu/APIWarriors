package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;

import java.util.List;

public interface IMatchService {
    MatchResponseDTO getMatchById(Integer matchId);
    List<MatchResponseDTO> getMatches();
    MatchResponseDTO createMatch(MatchRequestDTO newMatch);
    MatchResponseDTO setMatchWinner(Integer matchId, Integer playerId);
    Boolean deleteMatch(Integer matchId);
}
