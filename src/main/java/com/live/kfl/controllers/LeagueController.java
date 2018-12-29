package com.live.kfl.controllers;

import com.live.kfl.entities.LeagueEntity;
import com.live.kfl.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LeagueController {
    @Autowired
    LeagueService leagueService;

    @GetMapping("/test")
    public String testMethod()
    {
        return "Tested successfully";
    }

    // Validate League
    @GetMapping("/validateLeague/{id}")
    public Boolean validateLeague(@PathVariable int id) {
        return leagueService.validateLeague(id);
    }

    // Get All Values
    @GetMapping("/all")
    public List<LeagueEntity> getAll() {
        return leagueService.getAll();
    }

    // Create New league
    @PostMapping("/createLeague")
    public Map<String, Object> createLeague(@RequestBody Map<String, Object> leagueInfo) {
        return leagueService.createLeague(leagueInfo);

    }

}
