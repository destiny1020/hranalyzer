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

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.destiny1020.hranalyzer.rest.serializer.RecordSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "RECORD")
@JsonSerialize(using = RecordSerializer.class)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK", updatable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
    private Integer evaluationType;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERVISOR_ID")
    private Employee supervisor;

    public Record() {

    }

    public Record(Term term, Employee employee, Division division,
            Department department, Group group, Team team, String orgName,
            TitleClass titleClass, TitleRank titleRank,
            Integer evaluationClass, Integer evaluationType, Employee supervisor) {
        this.term = term;
        this.employee = employee;
        this.division = division;
        this.department = department;
        this.group = group;
        this.team = team;
        this.orgName = orgName;
        this.titleClass = titleClass;
        this.titleRank = titleRank;
        this.evaluationClass = evaluationClass;
        this.evaluationType = evaluationType;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public Term getTerm() {
        return term;
    }

    @JsonIgnore
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

    @JsonIgnore
    public Employee getSupervisor() {
        return supervisor;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluatonType) {
        this.evaluationType = evaluatonType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
