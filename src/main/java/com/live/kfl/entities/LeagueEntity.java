package com.live.kfl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "LEAGUE_INFO")
@Getter
@Setter

public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int LEAGUE_ID;

    @NotBlank
    String LEAGUE_NAME;

    @NotBlank
    String IS_ACTIVE;

    Timestamp UPDATE_DTM;

    @NotBlank
    String LEAGUE_CODE;

    @NotBlank
    String VALID_UPTO;
}
