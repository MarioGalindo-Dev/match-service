package com.soccer.matches.service.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name="match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MatchTeamInfo> TeamsThatPlayed;
    private Integer localTeamID;
    private Integer visitorTeamID;
    private Integer winnerOfTheMatchID;// if has id there was a winner, if not it is a draw
    private Integer goalsScoredByLocalTeam;
    private Integer goalsScoredByVisitorTeam;

    private String fieldNumber;
    private Date dateOfTheMatch;
    private String MatchHour;
}
