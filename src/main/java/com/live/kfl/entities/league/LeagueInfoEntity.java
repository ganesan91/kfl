package com.live.kfl.entities.league;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LEAGUE_INFO")
@Getter
@Setter
public class LeagueInfoEntity {
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

  @NotNull
  @Column(name = "USER_ID")
  int userId;

  @Column(name = "UPDATE_DTM")
  Timestamp updateDtm;

  @NotBlank
  @Column(name = "LEAGUE_CODE")
  String leagueCode;

  @NotBlank
  @Column(name = "VALID_UPTO")
  String validUpto;
}
