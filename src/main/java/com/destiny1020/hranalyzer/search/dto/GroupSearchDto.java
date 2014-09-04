package com.destiny1020.hranalyzer.search.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GroupSearchDto {

    // department
    private Integer d;

    // page and page size
    private Integer p;
    private Integer si;

    public Integer getDepartment() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getPage() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getSize() {
        return si;
    }

    public void setSi(Integer si) {
        this.si = si;
    }

    public boolean isPaging() {
        return p != null && si != null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
