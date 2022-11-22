package com.soccer.matches.service.client;


import com.soccer.matches.service.configuration.FeignClientConfig;
import com.soccer.matches.service.external.entities.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "TEAM-CLIENT",url="${team.service.url}",configuration = FeignClientConfig.class)

public interface TeamFeignClient {

    @GetMapping(value="/teams/{teamID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    Team getTeam(@PathVariable(value="teamID") String id);

    @GetMapping(value="/teams/getAllTeams/",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Team> getTeams();

}
