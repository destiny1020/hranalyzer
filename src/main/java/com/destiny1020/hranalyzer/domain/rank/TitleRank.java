package com.destiny1020.hranalyzer.domain.rank;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TITLE_RANK")
public class TitleRank extends TitleBase {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "TITLE_CLASS_ID")
    private TitleClass titleClass;

    public TitleClass getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(TitleClass titleClass) {
        this.titleClass = titleClass;
    }

}
