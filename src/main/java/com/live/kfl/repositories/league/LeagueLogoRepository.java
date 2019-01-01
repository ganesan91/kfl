package com.live.kfl.repositories.league;

import com.live.kfl.entities.league.LeagueLogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueLogoRepository extends JpaRepository<LeagueLogoEntity, Integer> {
}
