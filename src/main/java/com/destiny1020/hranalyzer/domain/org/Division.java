package com.destiny1020.hranalyzer.domain.org;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.xls.HRElement;

@Entity
@Table(name = "DIVISION")
public class Division extends OrganizationBase {

    @OneToMany(mappedBy = "division")
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public Division() {

    }

    public Division(HRElement element, Term term) {
        this.name = element.getDivision().trim().replaceAll(" +", " ");
        this.beginTerm = term;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}
