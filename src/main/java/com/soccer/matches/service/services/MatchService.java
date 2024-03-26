package com.soccer.matches.service.services;



import com.soccer.matches.service.entities.Match;
import com.soccer.matches.service.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Player service implement.
 */
@Service
public class MatchService {
     @Autowired
     private MatchRepository matchRepository;
   /*  @Autowired
     private TeamFeignClient teamFeignClient;*/



    public List<Match> listAllMatches() {
        return matchRepository.findAll();
    }


    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    public List<Match> getMatchesById(Integer id) {
        return matchRepository.findByTeamId(id);
    }

  /*  public ResponseTemplateVO getPlayerAndTeamById(Integer id)
    {
        var player= playerRepository.findById(id).get();
        var team =teamFeignClient.getTeam(String.valueOf(player.getTeamID()));
        var responseTemplate = new ResponseTemplateVO();
        responseTemplate.setPlayer(player);
        responseTemplate.setTeam(team);
        return responseTemplate;
    }*/

    public Match saveMatch(Match match)
    {
        match = matchRepository.save(match);
        return match;
    }



    public void deleteMatch(Integer id)
    {
        matchRepository.deleteById(id);
    }

}
