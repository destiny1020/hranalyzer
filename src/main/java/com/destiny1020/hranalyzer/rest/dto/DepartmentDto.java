package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.org.Department;

public class DepartmentDto extends BaseDto {

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }

}
