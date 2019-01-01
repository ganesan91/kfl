package com.live.kfl.entities.team;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "TEAM_INFO")
@Getter
@Setter
public class TeamInfoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TEAM_ID")
  int teamId;

  @NotNull
  @Column(name = "LEAGUE_ID")
  int leagueId;

    @NotNull
    @Column(name = "PIN")
    int pin;

  @NotBlank
  @Column(name = "TEAM_NAME")
  String teamName;

  @NotNull
  @Column(name = "AMOUNT_IN_TREASURY")
  Float amountInTreasury;

  @Column(name = "UPDATE_DTM")
  Timestamp updateDtm;

}
