package com.soccer.matches.service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "positions_table")
public class PositionsTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany(mappedBy = "positionsTable", cascade = CascadeType.ALL)
    @OneToOne(targetEntity = TeamPosition.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeamPosition> teamPositionRows = new HashSet<>();

    @Column(name = "table_position")
    private Integer tablePosition;

    @Column(name = "team_id")
    private Integer teamID;

    @Column(name = "journey_num")
    private Integer journeyNum;


}
