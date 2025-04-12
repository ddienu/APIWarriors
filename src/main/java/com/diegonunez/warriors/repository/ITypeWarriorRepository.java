package com.diegonunez.warriors.repository;

import com.diegonunez.warriors.entity.TypeWarrior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeWarriorRepository extends JpaRepository<TypeWarrior, Integer> {
}
