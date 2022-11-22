package com.soccer.matches.service.services;

import com.soccer.matches.service.entities.Match;
import com.soccer.matches.service.entities.MatchTeamInfo;
import com.soccer.matches.service.repositories.MatchRepository;
import com.soccer.matches.service.repositories.MatchTeamInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchTeamInfoService {

    @Autowired
    private MatchTeamInfoRepository matchRepository;



    public List<MatchTeamInfo> listAllMatches() {
        return matchRepository.findAll();
    }


    public MatchTeamInfo getMatchById(Integer matchID) {
        return matchRepository.findById(matchID).get();
    }
    public List<MatchTeamInfo> getMatchTeamInfoByTeamId(Integer teamID) {
        return matchRepository.findByTeamID(teamID);
    }
}
