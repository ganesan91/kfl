package com.live.kfl.entities.league;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LEAGUE_MEMBER")
@Getter
@Setter
public class LeagueMemberEntity {
  @Id
  @Column(name = "LEAGUE_ID")
  int leagueId;

  @NotNull
  @Column(name = "USER_ID")
  int userId;

  @NotBlank
  @Column(name = "TYPE")
  String type;
}
