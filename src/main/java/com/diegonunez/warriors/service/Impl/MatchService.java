package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Match.request.MatchRequestDTO;
import com.diegonunez.warriors.dto.Match.request.MatchRequestWinnerDTO;
import com.diegonunez.warriors.dto.Match.response.MatchPlayerResponseDTO;
import com.diegonunez.warriors.dto.Match.response.MatchResponseDTO;
import com.diegonunez.warriors.dto.Match.response.MatchUserResponseDTO;
import com.diegonunez.warriors.dto.Request.JoinMatchRequestDTO;
import com.diegonunez.warriors.dto.Response.*;
import com.diegonunez.warriors.entity.Match;
import com.diegonunez.warriors.entity.Player;
import com.diegonunez.warriors.exception.Unchecked.PlayerException;
import com.diegonunez.warriors.repository.IMatchRepository;
import com.diegonunez.warriors.repository.IPlayerRepository;
import com.diegonunez.warriors.repository.IUserRepository;
import com.diegonunez.warriors.service.IMatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
                matchFounded.getName(),
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
                    null,
                matchFounded.getActualPlayers()
        );
    }

    @Override
    public List<MatchResponseDTO> getMatches() {

        return matchRepository.findAll().stream().map(
                match -> new MatchResponseDTO(
                        match.getId(),
                        match.getName(),
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

                                : null,
                        match.getActualPlayers()
                )
        ).toList();
    }

    @Override
    public String joinMatch(JoinMatchRequestDTO joinMatchPayload) {
        Match matchToJoin = matchRepository.findById(joinMatchPayload.getMatchId()).orElseThrow(
                () -> new EntityNotFoundException("Match not found")
        );

        Player playerFounded = playerRepository.findById(joinMatchPayload.getPlayerId()).orElseThrow(
                () -> new EntityNotFoundException("Player not found")
        );

        Player botPlayer = playerRepository.findById(12).orElseThrow(
                () -> new EntityNotFoundException("Bot player not found")
        );

        if(!matchToJoin.getCode().equals(joinMatchPayload.getMatchCode())){
            throw new PlayerException("Incorrect provided code");
        }

        List<Player> currentPlayers = matchToJoin.getPlayers();

        if (currentPlayers == null) {
            currentPlayers = new ArrayList<>();
        }

        if (currentPlayers.contains(playerFounded)) {
            throw new PlayerException("Player is already in the match");
        }

        currentPlayers.add(playerFounded);
        currentPlayers.add(botPlayer);

        matchToJoin.setPlayers(currentPlayers);
        matchToJoin.setActualPlayers(matchToJoin.getActualPlayers()+2);

        matchRepository.save(matchToJoin);

        return "Player joined the match successfully";
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
        matchToSave.setPlayers(null);
        matchToSave.setName(newMatch.getName());
/*        matchToSave.setPlayers(newMatch.getPlayersId().stream().map(
                playerId -> playerRepository.findById(playerId).orElseThrow(
                        () -> new EntityNotFoundException("Player with ID: "+playerId+" not found")
                )
        ).collect(Collectors.toList()));*/
        matchToSave.setMaxPlayers(2);
        matchToSave.setActualPlayers(0);
        matchToSave.setWinner(null);

        matchRepository.save(matchToSave);

        return new MatchResponseDTO(
                matchToSave.getId(),
                matchToSave.getName(),
                matchToSave.getCode(),
                matchToSave.getActive(),
                matchToSave.getCreatedAt(),
                new MatchUserResponseDTO(
                        matchToSave.getCreatedBy().getUserId(),
                        matchToSave.getCreatedBy().getEmail()
                ),
                /*matchToSave.getPlayers().stream().map(
                        player -> new MatchPlayerResponseDTO(
                                player.getPlayerId(),
                                player.getNickname()
                        )
                ).toList(),*/
                null,
                matchToSave.getMaxPlayers(),
                null,
                matchToSave.getActualPlayers()
        );
    }

    @Override
    public MatchResponseDTO simulateBattle(MatchRequestWinnerDTO matchPayload) {

        Match matchFounded = matchRepository.findById(matchPayload.getMatchId()).orElseThrow(
                () -> new EntityNotFoundException("Match with ID: "+matchPayload.getMatchId()+" not found")
        );

        Player playerOne = playerRepository.findById(matchPayload.getPlayersIds()[0]).orElseThrow(
                () -> new EntityNotFoundException("Player with ID: "+matchPayload.getPlayersIds()[0]+ "not found")
        );

        Player playerTwo = playerRepository.findById(matchPayload.getPlayersIds()[1]).orElseThrow(
                () -> new EntityNotFoundException("Player with ID: "+matchPayload.getPlayersIds()[1]+ "not found")
        );

        matchFounded.setActive(Boolean.FALSE);
        Integer playerWinnerId = generateRandomWinner(playerOne.getPlayerId(), playerTwo.getPlayerId());
        if(playerWinnerId.equals(playerOne.getPlayerId())){
            matchFounded.setWinner(playerOne);
        }else{
            matchFounded.setWinner(playerTwo);
        }

        matchRepository.save(matchFounded);

        return new MatchResponseDTO(
                matchFounded.getId(),
                matchFounded.getName(),
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
                ),
                matchFounded.getActualPlayers()
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

    private Integer generateRandomWinner(Integer playerOne, Integer playerTwo){
        Random random = new Random();
        return random.nextBoolean() ? playerOne : playerTwo;
    }
}
