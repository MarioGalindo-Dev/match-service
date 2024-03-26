package com.soccer.matches.service.repositories;


import com.soccer.matches.service.entities.MatchTeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;




public interface MatchTeamInfoRepository extends JpaRepository<MatchTeamInfo, Integer> {

}
