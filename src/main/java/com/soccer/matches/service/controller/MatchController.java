package com.soccer.matches.service.controller;

import com.soccer.matches.service.entities.Match;
import com.soccer.matches.service.services.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@Slf4j
@EnableFeignClients
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/")
    public Match saveTeam(@RequestBody Match player)
    {
        log.info("saving match info");
        matchService.saveMatch(player);
        return matchService.saveMatch(player);
    }

    @GetMapping("/getAllMatches/")
    public List<Match> getAllPlayers()
    {
        return matchService.listAllMatches();
    }


    @DeleteMapping("/{matchID}")
    public List<Match> deleteAMatch(@PathVariable("matchID") int matchID)
    {
        matchService.deleteMatch(matchID);
        return matchService.listAllMatches();
    }
    @PutMapping("/editMatch/")
    public Match findTeam(@RequestBody Match match)
    {
        if(matchService.getMatchById(match.getId()).isPresent())
        {
            match = matchService.saveMatch(match);
        }
        return match;
    }


}
