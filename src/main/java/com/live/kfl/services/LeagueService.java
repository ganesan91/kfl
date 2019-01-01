package com.live.kfl.services;

import com.fasterxml.jackson.core.util.InternCache;
import com.live.kfl.entities.league.LeagueInfoEntity;
import com.live.kfl.entities.league.LeagueLimitEntity;
import com.live.kfl.entities.league.LeagueLogoEntity;
import com.live.kfl.entities.league.LeagueMemberEntity;
import com.live.kfl.entities.team.TeamInfoEntity;
import com.live.kfl.entities.team.TeamPreferenceEmbedded;
import com.live.kfl.entities.team.TeamPreferenceEntity;
import com.live.kfl.entities.team.UserTeamInfoEntity;
import com.live.kfl.repositories.league.LeagueInfoRepository;
import com.live.kfl.repositories.league.LeagueLimitRepository;
import com.live.kfl.repositories.league.LeagueLogoRepository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.live.kfl.repositories.league.LeagueMemberRepository;
import com.live.kfl.repositories.team.TeamInfoRepository;
import com.live.kfl.repositories.team.TeamPreferenceRepository;
import com.live.kfl.repositories.team.UserTeamInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.live.kfl.constants.LeagueConstants.*;

@Service
public class LeagueService {
    @Autowired
    LeagueInfoRepository leagueInfoRepository;

    @Autowired
    LeagueLimitRepository leagueLimitRepository;

    @Autowired
    LeagueLogoRepository leagueLogoRepository;

    @Autowired
    LeagueMemberRepository leagueMemberRepository;

    @Autowired
    TeamInfoRepository teamInfoRepository;

    @Autowired
    UserTeamInfoRepository userTeamInfoRepository;

    @Autowired
    TeamPreferenceRepository teamPreferenceRepository;

    Random random = new Random();

    public Boolean validateLeague(String leagueName) {
        return leagueInfoRepository.findByLeagueName(leagueName) != null ? true : false;
    }

    public List<LeagueInfoEntity> getAll() {
        return leagueInfoRepository.findAll();
    }

    /* To create league */
    public Map<String, Object> createLeague(Map<String, Object> leagueInfo) {
        leagueInfo = saveLeagueInfoEntity(leagueInfo);
        int leagueId = (int) leagueInfo.get(LEAGUE_ID);
        saveLeagueLimitEntity(leagueInfo);
        saveLeagueLogoEntity(leagueInfo);
        saveLeagueMemberEntity(leagueInfo, "A");
        Map<String, Object> responseApi = new HashMap<>();
        responseApi.put("user_id", leagueInfo.get("user_id"));
        responseApi.put(LEAGUE_ID, leagueId);
        responseApi.put(LEAGUE_NAME, leagueInfo.get(LEAGUE_NAME));
        responseApi.put(LEAGUE_CODE, leagueInfo.get(LEAGUE_CODE));
        return responseApi;
    }

    /* To join league */
    public Map<String, Object> joinLeague(Map<String, Object> leagueInfo) {
        Map<String, Object> teamDetails = new HashMap<>();
        Map<String, Object> teams = new HashMap<>();

        saveLeagueMemberEntity(leagueInfo, "M");
        List<TeamInfoEntity> teamInfoEntities = teamInfoRepository.findByLeagueId((int) leagueInfo.get(LEAGUE_ID));

        for (TeamInfoEntity teamInfoEntity : teamInfoEntities) {
            teams.put("team" + teamInfoEntity.getTeamId(), getTeamDetails(teamInfoEntity));
        }
        teamDetails.put("teamDetails", teams);
        Optional<LeagueLimitEntity> leagueLimitEntity = leagueLimitRepository.findById((int) leagueInfo.get(LEAGUE_ID));
        LeagueLimitEntity option = new LeagueLimitEntity();
        if (leagueLimitEntity.isPresent()) {
            option = leagueLimitEntity.get();
        }
        teamDetails.put("maxTeamLimitReached", teamInfoEntities.size() == option.getTeamSize() ? true : false);
        return teamDetails;

    }

    public Map<String, Object> getTeamDetails(TeamInfoEntity teamInfoEntity) {
        Map<String, Object> team = new HashMap<>();
        List<UserTeamInfoEntity> userTeamInfoEntities = userTeamInfoRepository.findByIdTeamId(teamInfoEntity.getTeamId());
        team.put("teamName", teamInfoEntity.getTeamName());
        Map<String, Object> members = new HashMap<>();
        for (UserTeamInfoEntity userTeamInfoEntity : userTeamInfoEntities) {
            Map<String, Object> member = new HashMap<>();
            member.put("userId", userTeamInfoEntity.getId().getUserId());
            members.put("member" + userTeamInfoEntity.getId().getUserId(), member);
        }
        team.put("members", members);
        List<TeamPreferenceEntity> teamPreferenceEntities = teamPreferenceRepository.findByIdTeamId(teamInfoEntity.getTeamId());
        Map<String, Object> preference = new HashMap<>();
        for (TeamPreferenceEntity teamPreferenceEntity : teamPreferenceEntities) {
            if (teamPreferenceEntity.getId().getPreferenceName().equals("COLOR")) {
                preference.put("color", teamPreferenceEntity.getPreferenceCode());
            } else if (teamPreferenceEntity.getId().getPreferenceName().equals("LOGO")) {
                preference.put("logoId", teamPreferenceEntity.getPreferenceCode());
            }
        }
        team.put("preference", preference);
        return team;
    }

    //To save league logo entity
    public void saveLeagueLogoEntity(Map<String, Object> leagueInfo) {
        LeagueLogoEntity leagueLogoEntity = new LeagueLogoEntity();
        leagueLogoEntity.setLeagueId(((int) leagueInfo.get(LEAGUE_ID)));
        leagueLogoEntity.setImageId(leagueInfo.get("logo_id").toString());
        leagueLogoRepository.save(leagueLogoEntity);
    }

    //To save league limit entity
    public void saveLeagueLimitEntity(Map<String, Object> leagueInfo) {
        LeagueLimitEntity leagueLimitEntity = new LeagueLimitEntity();
        leagueLimitEntity.setLeagueId(((int) leagueInfo.get(LEAGUE_ID)));
        leagueLimitEntity.setPlayerLimit((int) leagueInfo.get("players_limit"));
        leagueLimitEntity.setTeamSize((int) leagueInfo.get("team_limit"));
        leagueLimitRepository.save(leagueLimitEntity);
    }

    //To save league member entity
    public void saveLeagueMemberEntity(Map<String, Object> leagueInfo, String type) {
        LeagueMemberEntity leagueMemberEntity = new LeagueMemberEntity();
        leagueMemberEntity.setLeagueId(((int) leagueInfo.get(LEAGUE_ID)));
        leagueMemberEntity.setUserId((int) leagueInfo.get("user_id"));
        leagueMemberEntity.setType(type);
        leagueMemberRepository.save(leagueMemberEntity);
    }

    //To save league info entity
    public Map<String, Object> saveLeagueInfoEntity(Map<String, Object> leagueInfo) {
        LeagueInfoEntity leagueInfoEntity = new LeagueInfoEntity();
        leagueInfoEntity.setLeagueName(leagueInfo.get(LEAGUE_NAME).toString());
        leagueInfoEntity.setLeagueCode(
                leagueInfo.get(LEAGUE_NAME).toString().substring(0, 3)
                        + "_"
                        + String.format("%04d", random.nextInt(1000)));
        leagueInfoEntity.setValidUpto(getValidUpto());
        leagueInfoEntity.setIsActive("1");
        leagueInfoEntity.setUpdateDtm(getCurrentTimestamp());
        leagueInfoEntity.setUserId((int) leagueInfo.get("user_id"));
        leagueInfoRepository.save(leagueInfoEntity);
        leagueInfo.put(LEAGUE_ID, leagueInfoEntity.getLeagueId());
        leagueInfo.put(LEAGUE_CODE, leagueInfoEntity.getLeagueCode());
        return leagueInfo;
    }

    //To get valid upto
    public String getValidUpto() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(nextYear);
    }

    //To get current time stamp
    public static Timestamp getCurrentTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    //To get league info
    public LeagueInfoEntity getLeague(String leagueCode) {
        return leagueInfoRepository.findByLeagueCode(leagueCode);
    }
}
