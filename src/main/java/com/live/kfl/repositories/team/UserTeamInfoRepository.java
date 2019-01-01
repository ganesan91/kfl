package com.live.kfl.repositories.team;

import com.live.kfl.entities.team.UserTeamInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTeamInfoRepository extends JpaRepository<UserTeamInfoEntity, Integer> {

    // To get Users in specific team.
    List<UserTeamInfoEntity> findByIdTeamId(int teamId);
}
