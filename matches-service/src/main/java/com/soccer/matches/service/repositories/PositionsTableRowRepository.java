package com.soccer.matches.service.repositories;

import com.soccer.matches.service.entities.PositionsTableRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsTableRowRepository  extends JpaRepository<PositionsTableRow, Integer> {
}
