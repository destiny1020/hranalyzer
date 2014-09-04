package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.org.Team;

public class TeamDto extends BaseDto {

    public TeamDto(Team team) {
        if (team != null) {
            this.id = team.getId();
            this.name = team.getName();
        }
    }

}
