package com.destiny1020.hranalyzer.domain.org;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.rest.serializer.DepartmentSerializer;
import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "DEPARTMENT")
@JsonSerialize(using = DepartmentSerializer.class)
public class Department extends OrganizationBase {

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    @OneToMany(mappedBy = "department")
    private List<Group> groups;

    @Column(name = "DEPARTMENT_CD")
    private String departmentCode;

    public Department() {

    }

    public Department(HRElement element, Term term) {
        this.name = StandardizeString.standardize(element.getDepartment());
        this.departmentCode = element.getOrgCode().trim();
        this.beginTerm = term;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

}
