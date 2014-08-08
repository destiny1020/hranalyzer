package com.destiny1020.hranalyzer.domain.org;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DIVISION")
public class Division extends OrganizationBase {

    @OneToMany(mappedBy = "division")
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}
