package com.destiny1020.hranalyzer.domain.rank;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TITLE_CLASS")
public class TitleClass extends TitleBase {

    @OneToMany(mappedBy = "titleClass")
    private List<TitleRank> titleRanks;

    public List<TitleRank> getTitleRanks() {
        return titleRanks;
    }

    public void setTitleRanks(List<TitleRank> titleRanks) {
        this.titleRanks = titleRanks;
    }

}
