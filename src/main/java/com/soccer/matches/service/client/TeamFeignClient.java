package com.soccer.matches.service.client;

import com.soccer.matches.service.configuration.FeignClientConfig;
import com.soccer.matches.service.response.TeamServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TEAM-CLIENT",url="${team.service.url}",fallback = ServiceFallBack.class, configuration = FeignClientConfig.class)
public interface TeamFeignClient
{

    @GetMapping(value="/teams/{teamID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    TeamServiceResponse getTeam(@PathVariable(value="teamID") String id);

    @GetMapping(value="/teams/getAllTeams/",consumes = MediaType.APPLICATION_JSON_VALUE)
    TeamServiceResponse getTeams();

}
