package com.live.kfl.repositories.league;

import com.live.kfl.entities.league.LeagueInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueInfoRepository extends JpaRepository<LeagueInfoEntity, String> {

  // To validate League Name
  LeagueInfoEntity findByLeagueName(String leagueName);

  // To Join league
  LeagueInfoEntity findByLeagueCode(String leagueCode);
}
