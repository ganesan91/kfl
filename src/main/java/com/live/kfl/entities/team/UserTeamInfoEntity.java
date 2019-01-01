package com.live.kfl.entities.team;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "USER_TEAM_INFO")
@Getter
@Setter
public class UserTeamInfoEntity {
    @EmbeddedId
    private UserTeamInfoEmbedded id;

    @Column(name = "UPDATE_DTM")
    Timestamp updateDtm;

    @NotBlank
    @Column(name = "USER_TYPE")
    String type;

}
