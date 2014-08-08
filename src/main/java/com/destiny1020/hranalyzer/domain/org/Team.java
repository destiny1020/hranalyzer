package com.destiny1020.hranalyzer.domain.org;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM")
public class Team extends OrganizationBase {

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
