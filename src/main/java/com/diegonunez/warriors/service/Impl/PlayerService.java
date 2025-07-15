package com.diegonunez.warriors.service.Impl;

import com.diegonunez.warriors.dto.Request.PlayerRequestDTO;
import com.diegonunez.warriors.dto.Response.*;
import com.diegonunez.warriors.entity.Player;
import com.diegonunez.warriors.entity.User;
import com.diegonunez.warriors.entity.Warrior;
import com.diegonunez.warriors.repository.IPlayerRepository;
import com.diegonunez.warriors.repository.IUserRepository;
import com.diegonunez.warriors.repository.IWarriorRepository;
import com.diegonunez.warriors.service.IPlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {

    private final IPlayerRepository playerRepository;
    private final IWarriorRepository warriorRepository;
    private final IUserRepository userRepository;

    public PlayerService(IPlayerRepository playerRepository, IWarriorRepository warriorRepository,IUserRepository userRepository ){
        this.playerRepository = playerRepository;
        this.warriorRepository = warriorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlayerResponseDTO findPlayerById(Integer playerId) {
        Player playerFounded = playerRepository.findById(playerId).orElseThrow(
                () -> new EntityNotFoundException("Player with ID: "+playerId+" not founded")
        );

        return new PlayerResponseDTO(
                playerFounded.getPlayerId(),
                playerFounded.getNickname(),
                playerFounded.getWarriorsSelected().stream().map(
                        warrior -> new WarriorResponseDTO(
                                warrior.getWarriorId(),
                                warrior.getWarriorName(),
                                warrior.getWarriorLife(),
                                warrior.getWarriorEnergy(),
                                new TypeWarriorResponseDTO(
                                        warrior.getTypeOfWarrior().getTypeWarriorId(),
                                        warrior.getTypeOfWarrior().getTypeWarriorName(),
                                        warrior.getTypeOfWarrior().getTypeWarriorDescription()
                                ),
                                warrior.getTypeOfPower().stream().map(
                                        power -> new TypePowerResponseDTO(
                                                power.getPowerId(),
                                                power.getPowerName(),
                                                power.getPowerDamage(),
                                                power.getPowerEnergyConsumed(),
                                                power.getPowerDescription()
                                        )
                                ).toList(),
                                new BreedWarriorResponseDTO(
                                        warrior.getBreedWarrior().getBreedId(),
                                        warrior.getBreedWarrior().getBreedName(),
                                        warrior.getBreedWarrior().getBreedDescription(),
                                        warrior.getBreedWarrior().getBreedResistance()
                                )
                        )
                ).toList(),
                new UserResponseDTO(
                        playerFounded.getUser().getUserId(),
                        playerFounded.getUser().getEmail(),
                        playerFounded.getUser().getPassword(),
                        new UserStatusResponseDTO(
                                playerFounded.getUser().getUserStatus().getId(),
                                playerFounded.getUser().getUserStatus().getName(),
                                playerFounded.getUser().getUserStatus().getDescription()
                        ),
                        new RoleResponseDTO(
                                playerFounded.getUser().getRole().getId(),
                                playerFounded.getUser().getRole().getName(),
                                playerFounded.getUser().getRole().getDescription()
                        )
                )
        );
    }

    @Override
    public List<PlayerResponseDTO> findAllPlayers() {
        return playerRepository.findAll().stream().map(
                player -> new PlayerResponseDTO(
                        player.getPlayerId(),
                        player.getNickname(),
                        player.getWarriorsSelected().stream().map(
                                warrior -> new WarriorResponseDTO(
                                        warrior.getWarriorId(),
                                        warrior.getWarriorName(),
                                        warrior.getWarriorLife(),
                                        warrior.getWarriorEnergy(),
                                        new TypeWarriorResponseDTO(
                                                warrior.getTypeOfWarrior().getTypeWarriorId(),
                                                warrior.getTypeOfWarrior().getTypeWarriorName(),
                                                warrior.getTypeOfWarrior().getTypeWarriorDescription()
                                        ),
                                        warrior.getTypeOfPower().stream().map(
                                                power -> new TypePowerResponseDTO(
                                                        power.getPowerId(),
                                                        power.getPowerName(),
                                                        power.getPowerDamage(),
                                                        power.getPowerEnergyConsumed(),
                                                        power.getPowerDescription()
                                                )
                                        ).toList(),
                                        new BreedWarriorResponseDTO(
                                                warrior.getBreedWarrior().getBreedId(),
                                                warrior.getBreedWarrior().getBreedName(),
                                                warrior.getBreedWarrior().getBreedDescription(),
                                                warrior.getBreedWarrior().getBreedResistance()
                                        )
                                )
                        ).toList(),
                        new UserResponseDTO(
                                player.getUser().getUserId(),
                                player.getUser().getEmail(),
                                player.getUser().getPassword(),
                                new UserStatusResponseDTO(
                                        player.getUser().getUserStatus().getId(),
                                        player.getUser().getUserStatus().getName(),
                                        player.getUser().getUserStatus().getDescription()
                                ),
                                new RoleResponseDTO(
                                        player.getUser().getRole().getId(),
                                        player.getUser().getRole().getName(),
                                        player.getUser().getRole().getDescription()
                                )
                        )
                )
        ).toList();
    }

    @Override
    public PlayerResponseDTO findPlayerByUserId(Integer userId) {
        Integer playerId = playerRepository.getPlayerIdByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("The user does not have a player associate")
        );

        return findPlayerById(playerId);
    }

    @Transactional
    @Override
    public PlayerResponseDTO createPlayer(PlayerRequestDTO newPlayer) {
        Player playerToAdd = new Player();
        playerToAdd.setNickname(newPlayer.getNickname());
        playerToAdd.setWarriorsSelected(newPlayer.getWarriorsIdSelected().stream().map(
                warriorId -> warriorRepository.findById(warriorId).orElseThrow(
                        () -> new EntityNotFoundException("Warrior with ID: "+warriorId+" not found")
                )
        ).toList());
        playerToAdd.setUser(
                userRepository.findById(newPlayer.getUserId()).orElseThrow(
                        () -> new EntityNotFoundException("User with ID: "+newPlayer.getUserId()+" not found")
                )
        );

        playerRepository.save(playerToAdd);

        return new PlayerResponseDTO(
                playerToAdd.getPlayerId(),
                playerToAdd.getNickname(),
                playerToAdd.getWarriorsSelected().stream().map(
                        warrior -> new WarriorResponseDTO(
                                warrior.getWarriorId(),
                                warrior.getWarriorName(),
                                warrior.getWarriorLife(),
                                warrior.getWarriorEnergy(),
                                new TypeWarriorResponseDTO(
                                        warrior.getTypeOfWarrior().getTypeWarriorId(),
                                        warrior.getTypeOfWarrior().getTypeWarriorName(),
                                        warrior.getTypeOfWarrior().getTypeWarriorDescription()
                                ),
                                warrior.getTypeOfPower().stream().map(
                                        power -> new TypePowerResponseDTO(
                                                power.getPowerId(),
                                                power.getPowerName(),
                                                power.getPowerDamage(),
                                                power.getPowerEnergyConsumed(),
                                                power.getPowerDescription()
                                        )
                                ).toList(),
                                new BreedWarriorResponseDTO(
                                        warrior.getBreedWarrior().getBreedId(),
                                        warrior.getBreedWarrior().getBreedName(),
                                        warrior.getBreedWarrior().getBreedDescription(),
                                        warrior.getBreedWarrior().getBreedResistance()
                                )
                        )
                ).toList(),
                new UserResponseDTO(
                        playerToAdd.getUser().getUserId(),
                        playerToAdd.getUser().getEmail(),
                        playerToAdd.getUser().getPassword(),
                        new UserStatusResponseDTO(
                                playerToAdd.getUser().getUserStatus().getId(),
                                playerToAdd.getUser().getUserStatus().getName(),
                                playerToAdd.getUser().getUserStatus().getDescription()
                        ),
                        new RoleResponseDTO(
                                playerToAdd.getUser().getRole().getId(),
                                playerToAdd.getUser().getRole().getName(),
                                playerToAdd.getUser().getRole().getDescription()
                        )
                )
        );
    }

    @Override
    public PlayerResponseDTO updatePlayer(Integer playerId, PlayerRequestDTO playerUpdated) {
        Player playerFounded = playerRepository.findById(playerId).orElseThrow(
                () -> new EntityNotFoundException("Player with ID: "+playerId+" not founded")
        );

        if(!playerFounded.getNickname().equals(playerUpdated.getNickname())){
            playerFounded.setNickname(playerUpdated.getNickname());
        }

        playerFounded.setWarriorsSelected(playerUpdated.getWarriorsIdSelected().stream().map(
                warriorId -> warriorRepository.findById(warriorId).orElseThrow(
                        () -> new EntityNotFoundException("Warrior with ID: "+warriorId+" not found")
                )
        ).collect(Collectors.toList())
        );

        playerFounded.setUser(userRepository.findById(playerUpdated.getUserId()).orElseThrow(
                () -> new EntityNotFoundException("User with ID: "+playerUpdated.getUserId()+" not found")
        ));

        playerRepository.save(playerFounded);

        return new PlayerResponseDTO(
                playerFounded.getPlayerId(),
                playerFounded.getNickname(),
                playerFounded.getWarriorsSelected().stream().map(
                        warrior -> new WarriorResponseDTO(
                                warrior.getWarriorId(),
                                warrior.getWarriorName(),
                                warrior.getWarriorLife(),
                                warrior.getWarriorEnergy(),
                                new TypeWarriorResponseDTO(
                                        warrior.getTypeOfWarrior().getTypeWarriorId(),
                                        warrior.getTypeOfWarrior().getTypeWarriorName(),
                                        warrior.getTypeOfWarrior().getTypeWarriorDescription()
                                ),
                                warrior.getTypeOfPower().stream().map(
                                        power -> new TypePowerResponseDTO(
                                                power.getPowerId(),
                                                power.getPowerName(),
                                                power.getPowerDamage(),
                                                power.getPowerEnergyConsumed(),
                                                power.getPowerDescription()
                                        )
                                ).toList(),
                                new BreedWarriorResponseDTO(
                                        warrior.getBreedWarrior().getBreedId(),
                                        warrior.getBreedWarrior().getBreedName(),
                                        warrior.getBreedWarrior().getBreedDescription(),
                                        warrior.getBreedWarrior().getBreedResistance()
                                )
                        )
                ).toList(),
                new UserResponseDTO(
                        playerFounded.getUser().getUserId(),
                        playerFounded.getUser().getEmail(),
                        playerFounded.getUser().getPassword(),
                        new UserStatusResponseDTO(
                                playerFounded.getUser().getUserStatus().getId(),
                                playerFounded.getUser().getUserStatus().getName(),
                                playerFounded.getUser().getUserStatus().getDescription()
                        ),
                        new RoleResponseDTO(
                                playerFounded.getUser().getRole().getId(),
                                playerFounded.getUser().getRole().getName(),
                                playerFounded.getUser().getRole().getDescription()
                        )
                )
        );


    }

    @Override
    public Boolean deletePlayer(Integer playerId) {
        Player playerFounded = playerRepository.findById(playerId).orElseThrow(
                () -> new EntityNotFoundException("Player with ID: "+playerId+" not founded")
        );

        playerRepository.delete(playerFounded);

        return true;
    }
}

