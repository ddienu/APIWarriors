package com.diegonunez.warriors.repository;

import com.diegonunez.warriors.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Integer> {
}
