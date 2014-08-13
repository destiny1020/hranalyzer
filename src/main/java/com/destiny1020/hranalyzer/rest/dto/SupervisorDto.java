package com.destiny1020.hranalyzer.rest.dto;

import com.destiny1020.hranalyzer.domain.Employee;

public class SupervisorDto extends BaseDto {

    private String fullname;

    public SupervisorDto(Employee supervisor) {
        this.id = supervisor.getId();
        this.name = supervisor.getName();
        this.fullname = supervisor.getFullname();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
