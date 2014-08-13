package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.rank.TitleClass;

public class TitleClassDto extends BaseDto {

    public TitleClassDto(TitleClass titleClass) {
        this.id = titleClass.getId();
        this.name = titleClass.getName();
    }

}
