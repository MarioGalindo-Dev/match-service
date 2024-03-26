package com.soccer.matches.service.repositories;

import com.soccer.matches.service.entities.TeamPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface TeamsPositionRepository extends JpaRepository<TeamPosition, Integer> {

}
