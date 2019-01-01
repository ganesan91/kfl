package com.live.kfl.repositories.team;

import com.live.kfl.entities.team.TeamInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamInfoRepository extends JpaRepository<TeamInfoEntity, Integer> {
    // To validate League Name
    List<TeamInfoEntity> findByLeagueId(int leagueId);
}
