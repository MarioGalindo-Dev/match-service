package com.soccer.matches.service.batch.steps;

public class CalculateMatchPWDL  {
  /*  private MatchRepository matchRepository;
    private PositionsTable positionsTable;
    private List<Team> teamList;
    private List<PositionRows> positionsTableRowsList;
    private PositionsTableRepository positionsTableRepository;
    private TeamsPositionRepository positionsRowRepository;
    public CalculateMatchPWDL(MatchRepository matchRepository,
                              PositionsTableRepository positionsTableRepository,
                              TeamsPositionRepository positionsRowRepository)
    {
        this.matchRepository = matchRepository;
        this.positionsTableRepository = positionsTableRepository;
        this.positionsRowRepository = positionsRowRepository;
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
        positionsTableRowsList = positionsTable.getTableRows();
        positionsTableRowsList.forEach(positionsTableRow-> calculateAllColumnsOfTable(positionsTableRow,matchRepository.findAllMatchesOfATeam(positionsTableRow.getTeamID())));

        positionsTable = positionsTableRepository.save(positionsTable);
        stepContribution.getStepExecution().getJobExecution().getExecutionContext().put("positionsTableID",positionsTable.getId());
        return RepeatStatus.FINISHED;
    }

    private void setValuesToZeroIfAreNull(PositionsRow positionsRow)
    {

        if(positionsRow.getMatchesWon() == null)
        {
            positionsRow.setMatchesWon(0);
        }
        if(positionsRow.getMatchesDrew() == null)
        {
            positionsRow.setMatchesDrew(0);
        }
        if(positionsRow.getMatchesLost() == null)
        {
            positionsRow.setMatchesLost(0);
        }
        if(positionsRow.getMatchesPlayed() == null)
        {
            positionsRow.setMatchesPlayed(0);
        }
        if(positionsRow.getGoalsDifference() == null)
        {
            positionsRow.setGoalsDifference(0);
        }
        if(positionsRow.getGoalsAgainst() == null)
        {
            positionsRow.setGoalsAgainst(0);
        }
        if(positionsRow.getGoalsInFavor() == null)
        {
            positionsRow.setGoalsInFavor(0);
        }
    }
    private void calculateAllColumnsOfTable(PositionsRow positionsTableRow, List<Match> matchList)
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


    }*/
}
