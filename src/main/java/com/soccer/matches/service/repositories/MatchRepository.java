package com.soccer.matches.service.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchTeamInfoRepository, Integer> {


}
