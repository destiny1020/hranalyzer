package com.destiny1020.hranalyzer.domain.rank;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;

@Entity
@Table(name = "TITLE_CLASS")
public class TitleClass extends TitleBase {

    @OneToMany(mappedBy = "titleClass")
    private List<TitleRank> titleRanks;

    public List<TitleRank> getTitleRanks() {
        return titleRanks;
    }

    public TitleClass() {

    }

    public TitleClass(HRElement element, Term term) {
        this.name = StandardizeString
                .standardize(element.getTitleClass(), true);
        this.beginTerm = term;
    }

    public void setTitleRanks(List<TitleRank> titleRanks) {
        this.titleRanks = titleRanks;
    }

}
