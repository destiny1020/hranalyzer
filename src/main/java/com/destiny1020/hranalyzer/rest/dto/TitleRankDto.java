package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.rank.TitleRank;

public class TitleRankDto extends BaseDto {

    public TitleRankDto(TitleRank titleRank) {
        this.id = titleRank.getId();
        this.name = titleRank.getName();
    }

}
