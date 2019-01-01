package com.live.kfl.entities.team;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserTeamInfoEmbedded implements Serializable {
    @Column(name = "USER_ID")
    int userId;

    @Column(name = "TEAM_ID")
    int teamId;

    public UserTeamInfoEmbedded() {
    }

    public UserTeamInfoEmbedded(int userId, int teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTeamId() {
        return teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTeamInfoEmbedded)) return false;
        UserTeamInfoEmbedded that = (UserTeamInfoEmbedded) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getTeamId(), that.getTeamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getTeamId());
    }
}
