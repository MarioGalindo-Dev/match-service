package com.soccer.matches.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "positions_table")
public class PositionsTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "positionsTable", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<PositionsTableRow> tableRows;
}
