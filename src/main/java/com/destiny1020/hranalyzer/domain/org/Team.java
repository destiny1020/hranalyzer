package com.destiny1020.hranalyzer.domain.org;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.rest.serializer.TeamSerializer;
import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "TEAM")
@JsonSerialize(using = TeamSerializer.class)
public class Team extends OrganizationBase {

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public Team() {

    }

    public Team(HRElement element, Term term) {
        this.name = StandardizeString.standardize(element.getTeam());
        this.beginTerm = term;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
