package com.soccer.matches.service.batch.steps;

import com.soccer.matches.service.entities.PositionsTable;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class OrderPositionsList {

  /*private PositionsTableRepository positionsTableRepository;
    private Optional<PositionsTable> positionsTable;
    public OrderPositionsList(PositionsTableRepository positionsTableRepository)
    {
        this.positionsTableRepository = positionsTableRepository;
    }

    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        var positionsTableID =(Integer) stepExecution.getJobExecution().getExecutionContext().get("positionsTableID");
        positionsTable = positionsTableRepository.findById(positionsTableID);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception
    {
       var tableRows = positionsTable.get().getTableRows();
        //Collections.sort(tableRows,Comparator.comparing(PositionsTableRow::getPoints).reversed());
        positionsTable.get().setTableRows(tableRows);
        positionsTableRepository.save(positionsTable.get());
        return RepeatStatus.FINISHED;
    }*/
}
