package com.soccer.matches.service.batch2try;

import com.soccer.matches.service.client.TeamFeignClient;
import com.soccer.matches.service.entities.PositionsTable;
import com.soccer.matches.service.entities.TeamPosition;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private SessionFactory sessionFactory;

    private TeamFeignClient teamFeignClient;

    private PositionsItemProcessor positionsItemProcessor;
    private TeamItemReader itemReader;
    private TeamPositionWriter teamPositionWriter;
    BatchConfig(TeamItemReader itemReader,
                JobBuilderFactory jobBuilderFactory,
                StepBuilderFactory stepBuilderFactory,
                SessionFactory sessionFactory,
                PositionsItemProcessor positionsItemProcessor,
                TeamPositionWriter teamPositionWriter,
                TeamFeignClient teamFeignClient)
    {

        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.sessionFactory = sessionFactory;
        this.positionsItemProcessor = positionsItemProcessor;
        this.teamFeignClient = teamFeignClient;
        this.itemReader = itemReader;
        this.positionsItemProcessor = positionsItemProcessor;
        this.teamPositionWriter = teamPositionWriter;
    }

    @Bean
    public Step calculateTablePosition() {
        return stepBuilderFactory.get("calculateTablePosition")
                .<TeamPosition, TeamPosition> chunk(2)
                .reader(itemReader)
                .processor(positionsItemProcessor)
                .writer(teamPositionWriter)
                .build();
    }

    @Bean
    public Job importJob() {
        return jobBuilderFactory.get("calculateTablePositionJob")
                .incrementer(new RunIdIncrementer())
                //.flow(teamsGetterStep()).on("Failed").fail()
                .flow(calculateTablePosition())
                .end()
                .build();
    }


}
