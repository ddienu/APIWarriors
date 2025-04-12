package com.diegonunez.warriors.repository;

import com.diegonunez.warriors.entity.Warrior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarriorRepository extends JpaRepository<Warrior, Integer> {

    @Query(value = "SELECT * FROM warrior w WHERE w.breed_id = :breedId", nativeQuery = true)
    List<Warrior> findWarriorsUsingBreed(@Param("breedId") Integer breedId);

    @Query(value = "SELECT COUNT(*) FROM warrior w INNER JOIN warrior_powers wp ON wp.warrior_id = w.warrior_id WHERE wp.power_id = :powerId", nativeQuery = true)
    Integer findWarriorsUsingPowers(@Param("powerId") Integer powerId);

    @Query(value = "SELECT COUNT(*) FROM warrior w INNER JOIN type_warrior tp ON tp.type_warrior_id = w.type_warrior_id WHERE tp.type_warrior_id = :typeWarriorId", nativeQuery = true)
    Integer findWarriorsUsingType(@Param("typeWarriorId") Integer typeWarriorId);
}
