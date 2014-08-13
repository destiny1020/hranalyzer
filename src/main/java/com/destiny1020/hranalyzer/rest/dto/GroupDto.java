package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.org.Group;

public class GroupDto extends BaseDto {

    public GroupDto(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }

}
