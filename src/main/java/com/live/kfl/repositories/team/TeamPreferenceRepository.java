package com.live.kfl.repositories.team;

import com.live.kfl.entities.team.TeamInfoEntity;
import com.live.kfl.entities.team.TeamPreferenceEmbedded;
import com.live.kfl.entities.team.TeamPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPreferenceRepository extends JpaRepository<TeamPreferenceEntity, Integer> {
    // To get Color preference of all teams
    List<TeamPreferenceEntity> findByIdTeamId(int teamId);
}
