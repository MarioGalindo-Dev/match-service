package com.soccer.matches.service.batch2try;

import com.soccer.matches.service.client.TeamFeignClient;
import com.soccer.matches.service.external.entities.Team;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamGetterStep implements Tasklet {

    private TeamFeignClient teamFeignClient;
    private List<Team> teamList;

    public TeamGetterStep(TeamFeignClient teamFeignClient) {
        this.teamFeignClient = teamFeignClient;
    }



    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        var teamServiceResponse = teamFeignClient.getTeams();
        if (teamServiceResponse.getStatus().equals("Success"))
        {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("teams", teamServiceResponse.getTeams());
        }
        return RepeatStatus.FINISHED;
    }
}