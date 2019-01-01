package com.live.kfl.repositories.league;

import com.live.kfl.entities.league.LeagueMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueMemberRepository extends JpaRepository<LeagueMemberEntity, Integer> {
}
