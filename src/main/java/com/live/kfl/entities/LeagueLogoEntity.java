package com.live.kfl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "LEAGUE_LOGO")
@Getter
@Setter

public class LeagueLogoEntity {
    @Id
    int LEAGUE_ID;

    @NotBlank
    String IMAGE_ID;

}
