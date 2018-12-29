package com.live.kfl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LEAGUE_TEAM_LIMIT")
@Getter
@Setter

public class LeagueLimitEntity {
    @Id
    int LEAGUE_ID;

    @NotNull
    int TEAM_SIZE;

    @NotNull
    int PLAYER_LIMIT;
}
