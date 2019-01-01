package com.live.kfl.controllers;

import com.live.kfl.entities.league.LeagueInfoEntity;
import com.live.kfl.services.LeagueService;
import com.live.kfl.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Controller for all team functionalities
@RestController
public class TeamController {
  @Autowired
  TeamService teamService;

  // Create New Team
  @PostMapping("/createTeam")
  public Map<String, Object> createTeam(@RequestBody Map<String, Object> teamInfo) {
    return teamService.createTeam(teamInfo);
  }

  // join team
  @PostMapping("/joinTeam")
  public Map<String, Object> joinTeam(@RequestBody Map<String, Object> teamInfo) {
    return teamService.joinTeam(teamInfo);
  }
}
