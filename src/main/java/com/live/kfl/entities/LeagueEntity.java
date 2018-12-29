package com.live.kfl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "LEAGUE_INFO")
@Getter
@Setter

public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LEAGUE_ID")
    int leagueId;

    @NotBlank
    @Column(name = "LEAGUE_NAME")
    String leagueName;

    @NotBlank
    @Column(name = "IS_ACTIVE")
    String isActive;

    @Column(name = "UPDATE_DTM")
    Timestamp updateDtm;

    @NotBlank
    @Column(name = "LEAGUE_CODE")
    String leagueCode;

    @NotBlank
    @Column(name = "VALID_UPTO")
    String validUpto;
}
