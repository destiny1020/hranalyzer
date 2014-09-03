package com.destiny1020.hranalyzer.search.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EmployeeSearchDto {

    // division
    private Integer d;

    // department
    private Integer de;

    // group
    private Integer g;

    // team
    private Integer t;

    // page and page size
    private int p;
    private int si;

    public Integer getDivision() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getDepartment() {
        return de;
    }

    public void setDe(Integer de) {
        this.de = de;
    }

    public Integer getGroup() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getTeam() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public int getPage() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getSize() {
        return si;
    }

    public void setSi(int si) {
        this.si = si;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
