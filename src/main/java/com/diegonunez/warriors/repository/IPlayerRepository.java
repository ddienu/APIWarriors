package com.diegonunez.warriors.repository;

import com.diegonunez.warriors.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Integer> {

    @Query(value = "SELECT P.player_id FROM Player P INNER JOIN Users U ON U.user_id = P.user_id WHERE U.user_id = ?", nativeQuery = true)
    Optional<Integer> getPlayerIdByUserId(Integer userId);
}
