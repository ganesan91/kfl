package com.live.kfl.repositories.league;

import com.live.kfl.entities.league.LeagueLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueLimitRepository extends JpaRepository<LeagueLimitEntity, Integer> {
}
