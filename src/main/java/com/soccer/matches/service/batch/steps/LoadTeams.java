package com.soccer.matches.service.batch.steps;

import com.soccer.matches.service.client.TeamFeignClient;
import com.soccer.matches.service.repositories.MatchRepository;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import com.soccer.matches.service.repositories.TeamsPositionRepository;

public class LoadTeams  {
    public LoadTeams(MatchRepository playerRepository,
                     TeamFeignClient teamFeignClient,
                     PositionsTableRepository positionsTableRepository,
                     TeamsPositionRepository teamsPositionRepository)
    {
      /*  this.playerRepository = playerRepository;
        this.teamFeignClient = teamFeignClient;
        this.positionsTableRepository = positionsTableRepository;
        this.teamsPositionRepository = teamsPositionRepository;*/
    }

 /*   private MatchRepository playerRepository;
    private TeamFeignClient teamFeignClient;
    private PositionsTable positionsTable;
    private List<Team> teamList;
    private PositionsTableRepository positionsTableRepository;
    private TeamsPositionRepository positionsRowRepository;
    private List positionsRowList;
    private ExitStatus exitStatus;
    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        var teamServiceResponse = teamFeignClient.getTeams();
       if(teamServiceResponse.getStatus().equals("Success"))
       {
           teamList = teamServiceResponse.getTeams();
           positionsTable = new PositionsTable();
           positionsTableRepository.save(positionsTable);
           positionsRowList = new ArrayList();
       }
       else
           {
               stepExecution.setExitStatus(ExitStatus.FAILED);
           }

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution)
    {

        return stepExecution.getExitStatus()!= null? stepExecution.getExitStatus(): ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        if(teamList != null)
        {
            teamList.forEach(teamItem ->
            {
                var currentPositionTableRow = new PositionsRow();
                currentPositionTableRow.setTeamID(teamItem.getId());
                positionsRowList.add(currentPositionTableRow);
            });
            positionsRowList = positionsRowRepository.save(positionsRowList);
            positionsTable.setTableRows(positionsRowList);
            stepContribution.getStepExecution().getJobExecution().getExecutionContext().put("positionsTableID",positionsTable.getId());

        }
        return RepeatStatus.FINISHED;
    }*/
}
