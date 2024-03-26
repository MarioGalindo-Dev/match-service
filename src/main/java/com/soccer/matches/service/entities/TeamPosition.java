package com.soccer.matches.service.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "team_position")
public class TeamPosition implements Comparable<TeamPosition>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PositionsTable.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "positions_table_id")
    private PositionsTable positionsTable;

    private Integer teamID;

    @Column(name = "matches_played")
    private Integer matchesPlayed = 0;

    @Column(name = "matches_won")
    private Integer matchesWon = 0;

    @Column(name = "matches_drew")
    private Integer matchesDrew = 0;

    @Column(name = "matches_lost")
    private Integer matchesLost = 0;

    @Column(name = "goals_in_favor")
    private Integer goalsInFavor = 0;

    @Column(name = "goals_against")
    private Integer goalsAgainst = 0;

    @Column(name = "goals_difference")
    private Integer goalsDifference = 0;

    @Column(name = "points")
    private Integer points = 0;

    @Column(name = "total_of_yellow_cards")
    private Integer totalOfYellowCards = 0;

    @Column(name = "total_of_red_cards")
    private Integer totalOfRedCards = 0;

   @Override
    public int compareTo(TeamPosition teamPosition) {
      // TeamPositionComparator comparator = new TeamPositionComparator();
       // return comparator.compare(this, teamPosition);
       return 1;
    }
    public TeamPosition(Integer teamID) {
        this.teamID = teamID;
        this.matchesPlayed = 0;
        this.matchesWon = 0;
        this.matchesDrew = 0;
        this.matchesLost = 0;
        this.goalsInFavor = 0;
        this.goalsAgainst = 0;
        this.goalsDifference = 0;
        this.points = 0;
        this.totalOfYellowCards = 0;
        this.totalOfRedCards = 0;
    }
}
