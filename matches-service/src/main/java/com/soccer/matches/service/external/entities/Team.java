package com.soccer.matches.service.external.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private Integer id;
    private String name;
    private List<Integer> players;
    private Integer playedGames;
    private Integer wonMatches;
    private Integer drawMatches;
    private Integer lostMatches;
    private Integer goalsInFavor;
    private Integer goalsAgainst;
    private Integer yellowCards;
    private Integer redCards;
    private Date joinDate;
    private Date leftDate;
}
