package com.soccer.matches.service.repositories;

import com.soccer.matches.service.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query(
            value = "SELECT * FROM match m WHERE m.local_teamid = ?1 or m.visitor_teamid = ?1",
            nativeQuery = true)
    List<Match> findAllMatchesOfATeam(Integer teamID);
}
