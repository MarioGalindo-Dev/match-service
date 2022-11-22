package com.soccer.matches.service.batch;

import com.soccer.matches.service.batch.steps.CalculateMatchPWDL;
import com.soccer.matches.service.batch.steps.LoadTeams;
import com.soccer.matches.service.batch.steps.OrderPositionsList;
import com.soccer.matches.service.client.TeamFeignClient;
import com.soccer.matches.service.repositories.MatchRepository;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import com.soccer.matches.service.repositories.PositionsTableRowRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@EnableFeignClients
public class TablePositionCalculator {
    private MatchRepository matchRepository;
    private TeamFeignClient teamFeignClient;
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private PositionsTableRepository positionsTableRepository;
    private PositionsTableRowRepository positionsTableRowRepository;

    TablePositionCalculator(JobBuilderFactory jobBuilderFactory,
                            StepBuilderFactory stepBuilderFactory,
                            MatchRepository  matchRepository,
                            TeamFeignClient teamFeignClient,
                            PositionsTableRepository positionsTableRepository,
                            PositionsTableRowRepository positionsTableRowRepository)
    {
        this.matchRepository = matchRepository;
        this.teamFeignClient = teamFeignClient;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.positionsTableRepository = positionsTableRepository;
        this.positionsTableRowRepository = positionsTableRowRepository;
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
                        positionsTableRowRepository))
                .build();
    }

    @Bean
    public Step calculateGamesPWDL() //played, win,draw, lost,
    {
        return this.stepBuilderFactory.get("calculateGamesPWDL")
                .allowStartIfComplete(true)
                .tasklet(new CalculateMatchPWDL(matchRepository,positionsTableRepository,positionsTableRowRepository))
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


}
