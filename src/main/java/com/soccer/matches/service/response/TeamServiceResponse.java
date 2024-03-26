package com.soccer.matches.service.response;

import com.soccer.matches.service.external.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamServiceResponse {
    private int id;
    private String Message;
    private String Status;
    private Team team;
    private List<Team> teams;
}
