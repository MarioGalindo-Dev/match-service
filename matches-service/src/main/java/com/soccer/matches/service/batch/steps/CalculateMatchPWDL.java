package com.soccer.matches.service.batch.steps;

import com.soccer.matches.service.entities.Match;
import com.soccer.matches.service.entities.MatchTeamInfo;
import com.soccer.matches.service.entities.PositionsTable;
import com.soccer.matches.service.entities.PositionsTableRow;
import com.soccer.matches.service.external.entities.Team;
import com.soccer.matches.service.repositories.MatchRepository;
import com.soccer.matches.service.repositories.MatchTeamInfoRepository;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import com.soccer.matches.service.repositories.PositionsTableRowRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CalculateMatchPWDL implements Tasklet, StepExecutionListener {
    private MatchRepository matchRepository;
    private PositionsTable positionsTable;
    private List<Team> teamList;
    private List<PositionsTableRow> positionsTableRowList;
    private PositionsTableRepository positionsTableRepository;
    private PositionsTableRowRepository positionsTableRowRepository;
    public CalculateMatchPWDL(MatchRepository matchRepository,
                              PositionsTableRepository positionsTableRepository,
                              PositionsTableRowRepository positionsTableRowRepository)
    {
        this.matchRepository = matchRepository;
        this.positionsTableRepository = positionsTableRepository;
        this.positionsTableRowRepository = positionsTableRowRepository;
    }

    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        var positionsTableID = (Integer)stepExecution.getJobExecution().getExecutionContext().get("positionsTableID");
       positionsTable = positionsTableRepository.findById(positionsTableID).get();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution)
    {
        positionsTable = positionsTableRepository.save(positionsTable);
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        positionsTableRowList = positionsTable.getTableRows();
        positionsTableRowList.forEach(positionsTableRow-> calculateAllColumnsOfTable(positionsTableRow,matchRepository.findAllMatchesOfATeam(positionsTableRow.getTeamID())));

        positionsTable = positionsTableRepository.save(positionsTable);
        stepContribution.getStepExecution().getJobExecution().getExecutionContext().put("positionsTableID",positionsTable.getId());
        return RepeatStatus.FINISHED;
    }

    private void setValuesToZeroIfAreNull(PositionsTableRow positionsTableRow)
    {

        if(positionsTableRow.getMatchesWon() == null)
        {
            positionsTableRow.setMatchesWon(0);
        }
        if(positionsTableRow.getMatchesDrew() == null)
        {
            positionsTableRow.setMatchesDrew(0);
        }
        if(positionsTableRow.getMatchesLost() == null)
        {
            positionsTableRow.setMatchesLost(0);
        }
        if(positionsTableRow.getMatchesPlayed() == null)
        {
            positionsTableRow.setMatchesPlayed(0);
        }
        if(positionsTableRow.getGoalsDifference() == null)
        {
            positionsTableRow.setGoalsDifference(0);
        }
        if(positionsTableRow.getGoalsAgainst() == null)
        {
            positionsTableRow.setGoalsAgainst(0);
        }
        if(positionsTableRow.getGoalsInFavor() == null)
        {
            positionsTableRow.setGoalsInFavor(0);
        }
    }
    private void calculateAllColumnsOfTable(PositionsTableRow positionsTableRow, List<Match> matchList)
    {

        setValuesToZeroIfAreNull(positionsTableRow);
        matchList.forEach(match->{
            int wonMatches = positionsTableRow.getMatchesWon();
            int drawMatches = positionsTableRow.getMatchesDrew();
            int lostMatches = positionsTableRow.getMatchesLost();
            int goalsInFavor = positionsTableRow.getGoalsInFavor();
            int goalsAgainst = positionsTableRow.getGoalsAgainst();

                if(match.getLocalTeamID().equals(positionsTableRow.getTeamID()))
                {
                        goalsInFavor+=match.getGoalsScoredByLocalTeam();
                        goalsAgainst+=match.getGoalsScoredByVisitorTeam();
                }
                else
                    {
                        goalsInFavor+=match.getGoalsScoredByVisitorTeam();
                        goalsAgainst+=match.getGoalsScoredByLocalTeam();

                    }


            if(match.getWinnerOfTheMatchID()==null)
            {
                drawMatches=drawMatches+1;

            }
            else if(match.getWinnerOfTheMatchID().equals(positionsTableRow.getTeamID()))
           {
              wonMatches=wonMatches+1;
           }
           else{
               lostMatches=lostMatches+1;
           }
            positionsTableRow.setMatchesPlayed(lostMatches+wonMatches+drawMatches);
            positionsTableRow.setMatchesLost(lostMatches);
            positionsTableRow.setMatchesWon(wonMatches);
            positionsTableRow.setMatchesDrew(drawMatches);
            positionsTableRow.setGoalsInFavor(goalsInFavor);
            positionsTableRow.setGoalsAgainst(goalsAgainst);
            positionsTableRow.setGoalsDifference(goalsInFavor-goalsAgainst);
            positionsTableRow.setPoints((wonMatches*3)+drawMatches);
        });


    }
}
