package com.soccer.matches.service.batch2try;
import com.soccer.matches.service.client.TeamFeignClient;
import com.soccer.matches.service.entities.PositionsTable;
import com.soccer.matches.service.entities.TeamPosition;
import com.soccer.matches.service.external.entities.Team;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;

@Component
public class TeamItemReader implements ItemReader<TeamPosition> , StepExecutionListener {

    private TeamFeignClient teamFeignClient;
    private List<Team> teamList;
    TeamPosition teamPosition ;
    PositionsTable positionsTable ;
    ListIterator<Team> listIterator;
    public TeamItemReader(TeamFeignClient teamFeignClient)
    {
        this.teamFeignClient = teamFeignClient;
    }

    @Override
    public TeamPosition read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {
        teamPosition = null;
        if(listIterator.hasNext())
        {
            Team team = listIterator.next();
            teamPosition = new TeamPosition(team.getId());
        }

        return teamPosition;
    }
    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        var teamServiceResponse = teamFeignClient.getTeams();
        if (teamServiceResponse.getStatus().equals("Success"))
        {
            teamList = teamServiceResponse.getTeams();
            listIterator = teamList.listIterator();
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution)
    {
        teamList = null;
        teamPosition = null;
        positionsTable = null;
        listIterator = null;
        return ExitStatus.COMPLETED;
    }
}
