package com.soccer.matches.service.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.context.annotation.Bean;
/*
public class TablePositionCalculator {
    private MatchRepository matchRepository;
    private TeamFeignClient teamFeignClient;
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private PositionsTableRepository positionsTableRepository;
    private TeamsPositionRepository teamsPositionRepository;

    TablePositionCalculator(JobBuilderFactory jobBuilderFactory,
                            StepBuilderFactory stepBuilderFactory,
                            MatchRepository  matchRepository,
                            TeamFeignClient teamFeignClient,
                            PositionsTableRepository positionsTableRepository,
                            TeamsPositionRepository teamsPositionRepository)
    {
        this.matchRepository = matchRepository;
        this.teamFeignClient = teamFeignClient;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.positionsTableRepository = positionsTableRepository;
        this.teamsPositionRepository = teamsPositionRepository;
    }


   @Bean
    public Job positionsTableCalculator() {
        return this.jobBuilderFactory.get("footballJob")
                .start(loadTeams())
                .next(calculateGamesPWDL())
                .next(orderPositionsList())
                .build();
    }

    @Bean
    public Step loadTeams() {
        return this.stepBuilderFactory.get("loadTeams")
                .tasklet(new LoadTeams(matchRepository,
                        teamFeignClient,
                        positionsTableRepository,
                        teamsPositionRepository))
                .build();
    }

    @Bean
    public Step calculateGamesPWDL() //played, win,draw, lost,
    {
        return this.stepBuilderFactory.get("calculateGamesPWDL")
                .allowStartIfComplete(true)
                .tasklet(new CalculateMatchPWDL(matchRepository,positionsTableRepository, teamsPositionRepository))
                .build();
    }
    @Bean
    public Step orderPositionsList() //played, win,draw, lost,
    {
        return this.stepBuilderFactory.get("orderPositionsList")
                .allowStartIfComplete(true)
                .tasklet(new OrderPositionsList(positionsTableRepository))
                .build();
    }


}*/
