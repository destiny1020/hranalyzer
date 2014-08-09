package com.destiny1020.hranalyzer.domain.org;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;

@Entity
@Table(name = "DIVISION")
@NamedQueries({ @NamedQuery(name = Division.FIND_DIVISION_BY_NAME, query = Division.FIND_DIVISION_BY_NAME_SQL) })
public class Division extends OrganizationBase {

    public static final String FIND_DIVISION_BY_NAME = "findDivisionByName";
    public static final String FIND_DIVISION_BY_NAME_SQL = "select d from Division d where d.name = :name";

    @OneToMany(mappedBy = "division")
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public Division() {

    }

    public Division(HRElement element, Term term) {
        this.name = StandardizeString.standardize(element.getDivision());
        this.beginTerm = term;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}
