package com.live.kfl.entities.team;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamPreferenceEmbedded implements Serializable {

    @Column(name = "TEAM_ID")
    int teamId;

    @NotBlank
    @Column(name = "PREFERENCE_NAME")
    String preferenceName;

    public TeamPreferenceEmbedded() {
    }

    public TeamPreferenceEmbedded(int teamId, String preferenceName) {
        this.teamId = teamId;
        this.preferenceName = preferenceName;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamPreferenceEmbedded)) return false;
        TeamPreferenceEmbedded that = (TeamPreferenceEmbedded) o;
        return Objects.equals(getTeamId(), that.getTeamId()) &&
                Objects.equals(getPreferenceName(), that.getPreferenceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeamId(), getPreferenceName());
    }
}
