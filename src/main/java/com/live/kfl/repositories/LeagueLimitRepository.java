package com.live.kfl.repositories;

import com.live.kfl.entities.LeagueLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueLimitRepository extends JpaRepository<LeagueLimitEntity, Integer> {
}
