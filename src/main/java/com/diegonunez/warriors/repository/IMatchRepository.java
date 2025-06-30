package com.diegonunez.warriors.repository;

import com.diegonunez.warriors.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatchRepository extends JpaRepository<Match, Integer> {
}
