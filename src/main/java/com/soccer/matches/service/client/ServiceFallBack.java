package com.soccer.matches.service.client;

import com.soccer.matches.service.response.TeamServiceResponse;
import org.springframework.stereotype.Component;

@Component
public class ServiceFallBack implements TeamFeignClient{
    @Override
    public TeamServiceResponse getTeam(String id)
    {

        return getGenericFailData();
    }

    @Override
    public TeamServiceResponse getTeams() {
        return getGenericFailData();
    }

    private TeamServiceResponse getGenericFailData()
    {
        var temServiceResponse = new TeamServiceResponse();
        temServiceResponse.setId(0);
        temServiceResponse.setMessage("Fail while calling team service");
        return temServiceResponse;
    }
}

