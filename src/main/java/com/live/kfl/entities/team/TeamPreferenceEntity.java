package com.live.kfl.entities.team;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "TEAM_PREFERENCE")
@Getter
@Setter
public class TeamPreferenceEntity {
    @EmbeddedId
    private TeamPreferenceEmbedded id;

    @NotNull
    @Column(name = "PREFERENCE_CODE")
    int preferenceCode;

    @Column(name = "UPDATE_DTM")
    Timestamp updateDtm;

}
