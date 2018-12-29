package com.live.kfl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    @Column(name = "LEAGUE_ID")
    int leagueId;

    @NotNull
    @Column(name = "TEAM_SIZE")
    int teamSize;

    @NotNull
    @Column(name = "PLAYER_LIMIT")
    int playerLimit;
}
