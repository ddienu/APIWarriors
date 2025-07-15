package com.diegonunez.warriors.service;

import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.request.MatchRequestWinnerDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;
import com.diegonunez.warriors.dto.Request.JoinMatchRequestDTO;

import java.util.List;

public interface IMatchService {
    MatchResponseDTO getMatchById(Integer matchId);
    List<MatchResponseDTO> getMatches();
    String joinMatch(JoinMatchRequestDTO joinMatchPayload);
    MatchResponseDTO createMatch(MatchRequestDTO newMatch);
    MatchResponseDTO simulateBattle(MatchRequestWinnerDTO matchPayload);
    Boolean deleteMatch(Integer matchId);
}
