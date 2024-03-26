package com.soccer.matches.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Entity
@AllArgsConstructor
@Data
@Table(name = "match_team_info")
public class MatchTeamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teamID;

    @ManyToOne
    @JoinColumn(name="match_id", nullable=false)
    private Match match;
    private HashMap<Integer, Integer> goalScorers;//id of the player and quantity of goals
    private HashMap<Integer, Integer> yellowCards;//id of the player and quantity of yellow cards
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> playersSentOff;//id of the player and quantity of yellow cards
    private HashMap<Integer, Integer> redCards;//id of the player and quantity of red cards
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> playersList;

    MatchTeamInfo()
    {
        goalScorers = new HashMap<>();
        yellowCards = new HashMap<>();
        redCards = new HashMap<>();
        playersList = new ArrayList<>();
        playersSentOff = new ArrayList<>();
    }

}
