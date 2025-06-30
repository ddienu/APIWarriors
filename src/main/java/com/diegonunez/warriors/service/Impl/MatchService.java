package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.response.MatchPlayerResponseDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;
import com.diegonunez.warriors.dto.Match.response.MatchUserResponseDTO;
import com.diegonunez.warriors.dto.Response.*;
import com.diegonunez.warriors.entity.Match;
import com.diegonunez.warriors.entity.Player;
import com.diegonunez.warriors.repository.IMatchRepository;
import com.diegonunez.warriors.repository.IPlayerRepository;
import com.diegonunez.warriors.repository.IUserRepository;
import com.diegonunez.warriors.service.IMatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchService implements IMatchService {

    private final IMatchRepository matchRepository;
    private final IUserRepository userRepository;
    private final IPlayerRepository playerRepository;

    public MatchService(IMatchRepository matchRepository, IUserRepository userRepository, IPlayerRepository playerRepository){
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public MatchResponseDTO getMatchById(Integer matchId) {
        Match matchFounded = matchRepository.findById(matchId).orElseThrow(
                () -> new EntityNotFoundException("Match with ID: "+matchId+" not found")
        );

        return new MatchResponseDTO(
                matchFounded.getId(),
                matchFounded.getCode(),
                matchFounded.getActive(),
                matchFounded.getCreatedAt(),
                new MatchUserResponseDTO(
                        matchFounded.getCreatedBy().getUserId(),
                        matchFounded.getCreatedBy().getEmail()
                ),
                matchFounded.getPlayers().stream().map(
                        player -> new MatchPlayerResponseDTO(
                                player.getPlayerId(),
                                player.getNickname()
                        )
                ).toList(),
                matchFounded.getMaxPlayers(),
                matchFounded.getWinner() != null
                ?
                    new MatchPlayerResponseDTO(
                            matchFounded.getWinner().getPlayerId(),
                            matchFounded.getWinner().getNickname()
                    )
                :
                    null
        );
    }

    @Override
    public List<MatchResponseDTO> getMatches() {

        return matchRepository.findAll().stream().map(
                match -> new MatchResponseDTO(
                        match.getId(),
                        match.getCode(),
                        match.getActive(),
                        match.getCreatedAt(),
                        new MatchUserResponseDTO(
                                match.getCreatedBy().getUserId(),
                                match.getCreatedBy().getEmail()
                        ),
                        match.getPlayers().stream().map(
                                player -> new MatchPlayerResponseDTO(
                                        player.getPlayerId(),
                                        player.getNickname()
                                )
                        ).toList(),
                        match.getMaxPlayers(),
                        match.getWinner() != null ?
                        new MatchPlayerResponseDTO(
                                match.getWinner().getPlayerId(),
                                match.getWinner().getNickname()
                        )

                                : null
                )
        ).toList();
    }

    @Override
    public MatchResponseDTO createMatch(MatchRequestDTO newMatch) {
        Match matchToSave = new Match();
        matchToSave.setCode(generateMatchCode());
        matchToSave.setActive(Boolean.TRUE);
        matchToSave.setCreatedBy(userRepository.findById(newMatch.getCreatedByUserId()).orElseThrow(
                () -> new EntityNotFoundException("User with ID: "+newMatch.getCreatedByUserId()+" not found")
        ));
        matchToSave.setCreatedAt(LocalDateTime.now());
        matchToSave.setPlayers(newMatch.getPlayersId().stream().map(
                playerId -> playerRepository.findById(playerId).orElseThrow(
                        () -> new EntityNotFoundException("Player with ID: "+playerId+" not found")
                )
        ).collect(Collectors.toList()));
        matchToSave.setMaxPlayers(2);
        matchToSave.setWinner(null);

        matchRepository.save(matchToSave);

        return new MatchResponseDTO(
                matchToSave.getId(),
                matchToSave.getCode(),
                matchToSave.getActive(),
                matchToSave.getCreatedAt(),
                new MatchUserResponseDTO(
                        matchToSave.getCreatedBy().getUserId(),
                        matchToSave.getCreatedBy().getEmail()
                ),
                matchToSave.getPlayers().stream().map(
                        player -> new MatchPlayerResponseDTO(
                                player.getPlayerId(),
                                player.getNickname()
                        )
                ).toList(),
                matchToSave.getMaxPlayers(),
                null
        );
    }

    @Override
    public MatchResponseDTO setMatchWinner(Integer matchId, Integer playerId) {

        Match matchFounded = matchRepository.findById(matchId).orElseThrow(
                () -> new EntityNotFoundException("Match with ID: "+matchId+" not found")
        );

        Player playerFounded = playerRepository.findById(playerId).orElseThrow(
                () -> new EntityNotFoundException("Player with IDL: "+playerId)
        );

        matchFounded.setActive(Boolean.FALSE);
        matchFounded.setWinner(playerFounded);

        matchRepository.save(matchFounded);

        return new MatchResponseDTO(
                matchFounded.getId(),
                matchFounded.getCode(),
                matchFounded.getActive(),
                matchFounded.getCreatedAt(),
                new MatchUserResponseDTO(
                        matchFounded.getCreatedBy().getUserId(),
                        matchFounded.getCreatedBy().getEmail()
                ),
                matchFounded.getPlayers().stream().map(
                        player -> new MatchPlayerResponseDTO(
                                player.getPlayerId(),
                                player.getNickname()
                        )
                ).toList(),
                matchFounded.getMaxPlayers(),
                new MatchPlayerResponseDTO(
                        matchFounded.getWinner().getPlayerId(),
                        matchFounded.getWinner().getNickname()
                )
        );

    }

    @Override
    public Boolean deleteMatch(Integer matchId) {
        Match matchFounded = matchRepository.findById(matchId).orElseThrow(
                () -> new EntityNotFoundException("Match with ID: "+matchId+" not found")
        );

        matchRepository.delete(matchFounded);

        return true;
    }

    private String generateMatchCode(){
        String[] chars = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            int result = random.nextInt(chars.length);
            sb.append(chars[result]);
        }
        return sb.toString();
    }
}
