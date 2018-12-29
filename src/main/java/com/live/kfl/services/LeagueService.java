package com.live.kfl.services;

import com.live.kfl.entities.LeagueEntity;
import com.live.kfl.entities.LeagueLimitEntity;
import com.live.kfl.entities.LeagueLogoEntity;
import com.live.kfl.repositories.LeagueEntitityRepository;
import com.live.kfl.repositories.LeagueLimitRepository;
import com.live.kfl.repositories.LeagueLogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LeagueService {
    @Autowired
    LeagueEntitityRepository leagueEntitityRepository;

    @Autowired
    LeagueLimitRepository leagueLimitRepository;

    @Autowired
    LeagueLogoRepository leagueLogoRepository;


    Random random = new Random();

    public Boolean validateLeague(@PathVariable int leagueID) {
        return leagueEntitityRepository.existsById(leagueID);
    }

    public List<LeagueEntity> getAll() {
        return leagueEntitityRepository.findAll();
    }

    public Map<String, Object> createLeague(Map<String, Object> leagueInfo) {
        int league_id = saveLeagueEntity(leagueInfo);
        saveLeagueLimitEntity(leagueInfo, league_id);
        saveLeagueLogoEntity(leagueInfo, league_id);
        return leagueInfo;
    }

    private void saveLeagueLogoEntity(Map<String,Object> leagueInfo, int league_id) {
        LeagueLogoEntity leagueLogoEntity = new LeagueLogoEntity();
        leagueLogoEntity.setLEAGUE_ID(league_id);
        leagueLogoEntity.setIMAGE_ID(leagueInfo.get("logo_id").toString());
        leagueLogoRepository.save(leagueLogoEntity);
    }

    private void saveLeagueLimitEntity(Map<String,Object> leagueInfo , int league_id) {
        LeagueLimitEntity leagueLimitEntity = new LeagueLimitEntity();
        leagueLimitEntity.setLEAGUE_ID(league_id);
        leagueLimitEntity.setPLAYER_LIMIT(Integer.valueOf(leagueInfo.get("players_limit").toString()));
        leagueLimitEntity.setTEAM_SIZE(Integer.valueOf(leagueInfo.get("team_limit").toString()));
        leagueLimitRepository.save(leagueLimitEntity);
    }

    public int saveLeagueEntity(Map<String, Object> leagueInfo)
    {
        LeagueEntity leagueEntity = new LeagueEntity();
        leagueEntity.setLEAGUE_NAME(leagueInfo.get("league_name").toString());
        leagueEntity.setLEAGUE_CODE(leagueInfo.get("league_name").toString().substring(0,3) + "_" +String.format("%04d", random.nextInt(1000)));
        leagueEntity.setVALID_UPTO(getValidUpto());
        leagueEntity.setIS_ACTIVE("1");
        leagueEntity.setUPDATE_DTM(getCurrentTimestamp());
        leagueEntitityRepository.save(leagueEntity);
        return leagueEntity.getLEAGUE_ID();
    }

    public String getValidUpto(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(nextYear);
    }

    public Timestamp getCurrentTimestamp()
    {
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        return ts;
    }
}
