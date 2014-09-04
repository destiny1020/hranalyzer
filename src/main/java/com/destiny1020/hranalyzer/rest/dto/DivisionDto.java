package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.org.Division;

public class DivisionDto extends BaseDto {

    public DivisionDto(Division division) {
        if (division != null) {
            this.id = division.getId();
            this.name = division.getName();
        }
    }

}
