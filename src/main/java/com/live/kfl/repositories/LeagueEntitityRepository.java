package com.live.kfl.repositories;

import com.live.kfl.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueEntitityRepository extends JpaRepository<LeagueEntity, Integer> {
}
