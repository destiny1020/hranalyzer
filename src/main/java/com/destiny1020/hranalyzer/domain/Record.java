package com.destiny1020.hranalyzer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.domain.rank.TitleRank;

@Entity
@Table(name = "RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK", updatable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "TERM_ID")
    private Term term;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "ORG_NAME")
    private String orgName;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_CLASS_ID")
    private TitleClass titleClass;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_RANK_ID")
    private TitleRank titleRank;

    @Column(name = "EVA_CLASS")
    private Integer evaluationClass;

    @Column(name = "EVA_TYPE")
    private Integer evaluatonType;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERVISOR_ID")
    private Employee supervisor;

    public Long getId() {
        return id;
    }

    public Term getTerm() {
        return term;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Division getDivision() {
        return division;
    }

    public Department getDepartment() {
        return department;
    }

    public Group getGroup() {
        return group;
    }

    public Team getTeam() {
        return team;
    }

    public TitleClass getTitleClass() {
        return titleClass;
    }

    public TitleRank getTitleRank() {
        return titleRank;
    }

    public String getOrgName() {
        return orgName;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public Integer getEvaluatonType() {
        return evaluatonType;
    }

    public void setEvaluatonType(Integer evaluatonType) {
        this.evaluatonType = evaluatonType;
    }
}
