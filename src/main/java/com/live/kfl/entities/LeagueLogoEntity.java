package com.live.kfl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    @Column(name = "LEAGUE_ID")
    int leagueId;

    @NotBlank
    @Column(name = "IMAGE_ID")
    String imageId;

}
