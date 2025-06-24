package com.diegonunez.warriors.repository;

import com.diegonunez.warriors.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserStatusRepository extends JpaRepository<UserStatus, Integer> {
}
