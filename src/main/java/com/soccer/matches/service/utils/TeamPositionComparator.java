package com.soccer.matches.service.utils;

import com.soccer.matches.service.entities.TeamPosition;

import java.util.Comparator;

public class TeamPositionComparator implements Comparator<TeamPosition> {
    @Override
    public int compare(TeamPosition tp1, TeamPosition tp2) {
        return Comparator.comparing(TeamPosition::getPoints)
                .thenComparingInt(TeamPosition::getGoalsDifference)
                .thenComparingInt(TeamPosition::getGoalsInFavor)
                .thenComparingInt(TeamPosition::getTotalOfRedCards)
                .thenComparingInt(TeamPosition::getTotalOfYellowCards)
                .compare(tp2, tp1);
    }
}