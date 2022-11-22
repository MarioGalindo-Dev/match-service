package com.soccer.matches.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "positions_table_row")
public class PositionsTableRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="positionstable_id", nullable=false)
    private PositionsTable positionsTable;
    private Integer tablePosition;
    private Integer teamID;
    private Integer matchesPlayed;
    private Integer matchesWon;
    private Integer matchesDrew;
    private Integer matchesLost;
    private Integer goalsInFavor;
    private Integer goalsAgainst;
    private Integer goalsDifference;
    private Integer points;
    private Integer totalOfYellowCards;
    private Integer totalOfRedCards;

}
