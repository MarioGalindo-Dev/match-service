package com.soccer.matches.service.batch2try;

import com.soccer.matches.service.entities.Match;
import com.soccer.matches.service.entities.TeamPosition;
import com.soccer.matches.service.repositories.MatchRepository;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import com.soccer.matches.service.repositories.TeamsPositionRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PositionsItemProcessor implements ItemProcessor<TeamPosition, TeamPosition> {

    MatchRepository matchRepository;

    PositionsTableRepository positionsTableRepository;
    TeamsPositionRepository teamsPositionRepository;
    public PositionsItemProcessor(MatchRepository matchRepository, TeamsPositionRepository teamsPositionRepository)
    {
        this.matchRepository = matchRepository;
        this.teamsPositionRepository = teamsPositionRepository;
    }

    @Override
    public TeamPosition process(TeamPosition teamPosition) throws Exception
    {
        calculateAllColumnsOfTable(teamPosition,matchRepository.findByTeamId(teamPosition.getTeamID()));

        return teamsPositionRepository.save(teamPosition);
    }


    private void calculateGoals(TeamPosition teamPosition, Match match) {
        int goalsInFavor = teamPosition.getGoalsInFavor();
        int goalsAgainst = teamPosition.getGoalsAgainst();

        if (match.getLocalTeamID().equals(teamPosition.getTeamID()))
        {
            goalsInFavor += match.getGoalsScoredByLocalTeam();
            goalsAgainst += match.getGoalsScoredByVisitorTeam();
        }
        else
            {
            goalsInFavor += match.getGoalsScoredByVisitorTeam();
            goalsAgainst += match.getGoalsScoredByLocalTeam();
        }

        teamPosition.setGoalsInFavor(goalsInFavor);
        teamPosition.setGoalsAgainst(goalsAgainst);
        teamPosition.setGoalsDifference(goalsInFavor - goalsAgainst);
    }

    private void calculateMatches(TeamPosition teamPosition, Match match)
    {
        int wonMatches = teamPosition.getMatchesWon();
        int drawMatches = teamPosition.getMatchesDrew();
        int lostMatches = teamPosition.getMatchesLost();

        if (match.getWinnerOfTheMatchID() == null) {
            drawMatches++;
        } else if (match.getWinnerOfTheMatchID().equals(teamPosition.getTeamID())) {
            wonMatches++;
        } else {
            lostMatches++;
        }

        teamPosition.setMatchesPlayed(lostMatches + wonMatches + drawMatches);
        teamPosition.setMatchesLost(lostMatches);
        teamPosition.setMatchesWon(wonMatches);
        teamPosition.setMatchesDrew(drawMatches);
        teamPosition.setPoints((wonMatches * 3) + drawMatches);
    }

    private void calculateAllColumnsOfTable(TeamPosition teamPosition, List<Match> matchList) {
        matchList.forEach(match -> {
            calculateGoals(teamPosition, match);
            calculateMatches(teamPosition, match);
        });
    }

}