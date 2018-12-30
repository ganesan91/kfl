package com.live.kfl.controllers;

import com.live.kfl.entities.LeagueInfoEntity;
import com.live.kfl.services.LeagueService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Controller for all League functionalities
@RestController
public class LeagueController {
  @Autowired LeagueService leagueService;

  // Validate League
  @GetMapping("/validateLeague/{leagueName}")
  public Boolean validateLeague(@PathVariable String leagueName) {
    return leagueService.validateLeague(leagueName);
  }

  // Get All Values
  @GetMapping("/all")
  public List<LeagueInfoEntity> getAll() {
    return leagueService.getAll();
  }

  // Create New league
  @PostMapping("/createLeague")
  public Map<String, Object> createLeague(@RequestBody Map<String, Object> leagueInfo) {
    return leagueService.createLeague(leagueInfo);
  }

  // Join league
  @GetMapping("/joinLeague/{leagueCode}")
  public LeagueInfoEntity joinLeague(@PathVariable String leagueCode) {
    return leagueService.joinLeague(leagueCode);
  }
}
