package com.soccer.matches.service.batch2try;

import com.soccer.matches.service.entities.PositionsTable;
import com.soccer.matches.service.entities.TeamPosition;
import com.soccer.matches.service.repositories.TeamsPositionRepository;
import com.soccer.matches.service.repositories.PositionsTableRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeamPositionWriter implements ItemWriter<TeamPosition> {
    private Set<TeamPosition> teamPositionsSet = new HashSet<>();
    private PositionsTableRepository  positionsTableRepository;
    private TeamsPositionRepository  teamsPositionRepository;
    public TeamPositionWriter(PositionsTableRepository  positionsTableRepository,TeamsPositionRepository teamsPositionRepository)
    {
        this.positionsTableRepository = positionsTableRepository;
        this.teamsPositionRepository = teamsPositionRepository;
    }
    public Set<TeamPosition> getTeamPositionsSet() {
        return teamPositionsSet;
    }

  /*  @Override
    public void write(List<? extends TeamPosition> items) throws Exception {
        teamPositionsSet.addAll(items);
       teamsPositionRepository.save(teamPositionsSet);

        PositionsTable positionsTable = new PositionsTable();
        positionsTable.setJourneyNum(1);
        positionsTable.setTeamPositionRows(teamPositionsSet);
        positionsTableRepository.save(positionsTable);
    }*/

    @Override
    public void write(List<? extends TeamPosition> items) throws Exception {
        List<TeamPosition> teamPositionsList = new ArrayList<>(items);
        List<TeamPosition> distinctTeamPositionsList = teamPositionsList.stream().distinct().collect(Collectors.toList());

       // teamPositionsSet.addAll(distinctTeamPositionsList);
        PositionsTable positionsTable = new PositionsTable();
        positionsTable.setJourneyNum(1);
      /*  distinctTeamPositionsList.stream().forEach(teamPosition ->
        {
            teamPosition.setPositionsTable(positionsTable);
            teamsPositionRepository.save(teamPosition);
        });*/
        //teamsPositionRepository.save(teamPosition);

       // positionsTable.setTeamPositionRows(teamPositionsSet);
        //positionsTableRepository.save(positionsTable);
    }
}
