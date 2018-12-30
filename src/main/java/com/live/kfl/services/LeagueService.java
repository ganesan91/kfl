package com.live.kfl.services;

import com.live.kfl.entities.LeagueEntity;
import com.live.kfl.entities.LeagueLimitEntity;
import com.live.kfl.entities.LeagueLogoEntity;
import com.live.kfl.repositories.LeagueEntitityRepository;
import com.live.kfl.repositories.LeagueLimitRepository;
import com.live.kfl.repositories.LeagueLogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.live.kfl.constants.LeagueConstants.*;

@Service
public class LeagueService {
    @Autowired
    LeagueEntitityRepository leagueEntitityRepository;

    @Autowired
    LeagueLimitRepository leagueLimitRepository;

    @Autowired
    LeagueLogoRepository leagueLogoRepository;


    Random random = new Random();

    public Boolean validateLeague(String leagueName) {
        return leagueEntitityRepository.findByLeagueName(leagueName) != null ? true : false;
    }

    public List<LeagueEntity> getAll() {
        return leagueEntitityRepository.findAll();
    }

    public Map<String, Object> createLeague(Map<String, Object> leagueInfo) {
        leagueInfo = saveLeagueEntity(leagueInfo);
        int leagueId = (int) leagueInfo.get(LEAGUE_ID);
        saveLeagueLimitEntity(leagueInfo, leagueId);
        saveLeagueLogoEntity(leagueInfo, leagueId);
        Map<String, Object> responseApi = new HashMap<>();
        responseApi.put("user_id", leagueInfo.get("user_id"));
        responseApi.put(LEAGUE_ID, leagueId);
        responseApi.put(LEAGUE_NAME, leagueInfo.get(LEAGUE_NAME));
        responseApi.put(LEAGUE_CODE, leagueInfo.get(LEAGUE_CODE));
        return responseApi;
    }

    private void saveLeagueLogoEntity(Map<String, Object> leagueInfo, int leagueId) {
        LeagueLogoEntity leagueLogoEntity = new LeagueLogoEntity();
        leagueLogoEntity.setLeagueId(leagueId);
        leagueLogoEntity.setImageId(leagueInfo.get("logo_id").toString());
        leagueLogoRepository.save(leagueLogoEntity);
    }

    private void saveLeagueLimitEntity(Map<String, Object> leagueInfo, int leagueId) {
        LeagueLimitEntity leagueLimitEntity = new LeagueLimitEntity();
        leagueLimitEntity.setLeagueId(leagueId);
        leagueLimitEntity.setPlayerLimit(Integer.valueOf(leagueInfo.get("players_limit").toString()));
        leagueLimitEntity.setTeamSize(Integer.valueOf(leagueInfo.get("team_limit").toString()));
        leagueLimitRepository.save(leagueLimitEntity);
    }

    public Map<String, Object> saveLeagueEntity(Map<String, Object> leagueInfo) {
        LeagueEntity leagueEntity = new LeagueEntity();
        leagueEntity.setLeagueName(leagueInfo.get(LEAGUE_NAME).toString());
        leagueEntity.setLeagueCode(leagueInfo.get(LEAGUE_NAME).toString().substring(0, 3) + "_" + String.format("%04d", random.nextInt(1000)));
        leagueEntity.setValidUpto(getValidUpto());
        leagueEntity.setIsActive("1");
        leagueEntity.setUpdateDtm(getCurrentTimestamp());
        leagueEntity.setUserId(Integer.valueOf(leagueInfo.get("user_id").toString()));
        leagueEntitityRepository.save(leagueEntity);
        leagueInfo.put(LEAGUE_ID, leagueEntity.getLeagueId());
        leagueInfo.put(LEAGUE_CODE, leagueEntity.getLeagueCode());
        return leagueInfo;
    }

    public String getValidUpto() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(nextYear);
    }

    public Timestamp getCurrentTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
