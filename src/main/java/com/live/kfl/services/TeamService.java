package com.live.kfl.services;

import com.live.kfl.entities.team.*;
import com.live.kfl.repositories.team.TeamInfoRepository;
import com.live.kfl.repositories.team.TeamPreferenceRepository;
import com.live.kfl.repositories.team.UserTeamInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.live.kfl.constants.LeagueConstants.*;

@Service
public class TeamService {
    @Autowired
    TeamInfoRepository teamInfoRepository;

    @Autowired
    UserTeamInfoRepository userTeamInfoRepository;

    @Autowired
    TeamPreferenceRepository teamPreferenceRepository;

    /* To create team */
    public Map<String, Object> createTeam(Map<String, Object> teamInfo) {
        teamInfo = saveTeamInfoEntity(teamInfo);
        saveUserTeamInfoEntity(teamInfo);
        saveTeamPreference(teamInfo, "color");
        saveTeamPreference(teamInfo, "logo_id");

        Map<String, Object> responseApi = new HashMap<>();
        responseApi.put("team_create_status", "Success");
        responseApi.put("team_id", teamInfo.get("team_id"));
        responseApi.put("team_name", teamInfo.get("team_name"));
        responseApi.put("pin", teamInfo.get("pin"));
        return responseApi;
    }

    //To save team info
    public Map<String, Object> saveTeamInfoEntity(Map<String, Object> teamInfo) {
        TeamInfoEntity teamInfoEntity = new TeamInfoEntity();
        teamInfoEntity.setLeagueId((int) teamInfo.get(LEAGUE_ID));
        teamInfoEntity.setTeamName(teamInfo.get("team_name").toString());
        teamInfoEntity.setUpdateDtm(LeagueService.getCurrentTimestamp());
        teamInfoEntity.setAmountInTreasury((float) 100);
        teamInfoEntity.setPin((int) teamInfo.get("pin"));
        teamInfoRepository.save(teamInfoEntity);
        teamInfo.put("team_id", teamInfoEntity.getTeamId());
        return teamInfo;
    }

    //To save to user team info
    public void saveUserTeamInfoEntity(Map<String, Object> teamInfo) {
        UserTeamInfoEntity userTeamInfoEntity = new UserTeamInfoEntity();
        UserTeamInfoEmbedded userTeamInfoEmbedded = new UserTeamInfoEmbedded((int) teamInfo.get("user_id"), (int) teamInfo.get("team_id"));
        userTeamInfoEntity.setId(userTeamInfoEmbedded);
        userTeamInfoEntity.setType("A");
        userTeamInfoEntity.setUpdateDtm(LeagueService.getCurrentTimestamp());
        userTeamInfoRepository.save(userTeamInfoEntity);
    }

    //To save to user team info
    public void saveTeamPreference(Map<String, Object> teamInfo, String preferenceName) {
        TeamPreferenceEntity teamPreferenceEntity = new TeamPreferenceEntity();
        TeamPreferenceEmbedded teamPreferenceEmbedded = new TeamPreferenceEmbedded((int) teamInfo.get("team_id"), preferenceName);
        teamPreferenceEntity.setId(teamPreferenceEmbedded);
        teamPreferenceEntity.setPreferenceCode((int) teamInfo.get(preferenceName));
        teamPreferenceEntity.setUpdateDtm(LeagueService.getCurrentTimestamp());
        teamPreferenceRepository.save(teamPreferenceEntity);
    }
}
