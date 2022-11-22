package com.soccer.matches.service.repositories;

import com.soccer.matches.service.entities.Match;
import com.soccer.matches.service.entities.PositionsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionsTableRepository extends JpaRepository<PositionsTable, Integer> {
}
