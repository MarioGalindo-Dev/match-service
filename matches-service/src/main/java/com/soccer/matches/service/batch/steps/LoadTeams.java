package com.soccer.matches.service.batch.steps;

import com.soccer.matches.service.client.TeamFeignClient;
import com.soccer.matches.service.entities.PositionsTable;
import com.soccer.matches.service.entities.PositionsTableRow;
import com.soccer.matches.service.external.entities.Team;
import com.soccer.matches.service.repositories.MatchRepository;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import com.soccer.matches.service.repositories.PositionsTableRowRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.ArrayList;
import java.util.List;

public class LoadTeams implements Tasklet, StepExecutionListener {
    public LoadTeams(MatchRepository playerRepository,
                     TeamFeignClient teamFeignClient,
                     PositionsTableRepository positionsTableRepository,
                     PositionsTableRowRepository positionsTableRowRepository)
    {
        this.playerRepository = playerRepository;
        this.teamFeignClient = teamFeignClient;
        this.positionsTableRepository = positionsTableRepository;
        this.positionsTableRowRepository = positionsTableRowRepository;
    }

    private MatchRepository playerRepository;
    private TeamFeignClient teamFeignClient;
    private PositionsTable positionsTable;
    private List<Team> teamList;
    private PositionsTableRepository positionsTableRepository;
    private PositionsTableRowRepository positionsTableRowRepository;
    private List positionsTableRowList;

    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        teamList = teamFeignClient.getTeams();

        positionsTable = new PositionsTable();
        positionsTableRepository.save(positionsTable);
        positionsTableRowList = new ArrayList<PositionsTableRow>();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {


        teamList.forEach(teamItem ->
        {
            var currentPositionTableRow = new PositionsTableRow();
            currentPositionTableRow.setPositionsTable(positionsTable);
            currentPositionTableRow.setTeamID(teamItem.getId());
            positionsTableRowList.add(currentPositionTableRow);
        });
        positionsTableRowList = positionsTableRowRepository.saveAll(positionsTableRowList);
        positionsTable.setTableRows(positionsTableRowList);
        stepContribution.getStepExecution().getJobExecution().getExecutionContext().put("positionsTableID",positionsTable.getId());
        return RepeatStatus.FINISHED;
    }
}
