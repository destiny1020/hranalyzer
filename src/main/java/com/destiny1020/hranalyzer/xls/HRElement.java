package com.destiny1020.hranalyzer.xls;

import jxl.Cell;

/**
 * Represent one row in the source xls. 
 */
public class HRElement {

    // 0
    private String eid;

    // 1
    private String name;
    // 2
    private String name2;

    // 4
    private String division;
    // 5
    private String department;
    // 6
    private String group;
    // 7
    private String team;
    // 8
    private String orgCode;
    // 9
    private String orgName;

    // 14
    private String titleClass;
    // 15
    private String titleRank;

    // 16
    private String evaClass;
    // 17
    private String evaType;

    // 19
    private String lid;

    // 20 --- supervisor eid
    private String seid;

    public HRElement(Cell[] row) {
        assert row != null && row.length == 23;

        this.eid = row[0].getContents();

        this.name = row[1].getContents();
        this.name2 = row[2].getContents();

        this.division = row[4].getContents();
        this.department = row[5].getContents();
        this.group = row[6].getContents();
        this.team = row[7].getContents();
        this.orgCode = row[8].getContents();
        this.orgName = row[9].getContents();

        this.titleClass = row[14].getContents();
        this.titleRank = row[15].getContents();

        this.evaClass = row[16].getContents();
        this.evaType = row[17].getContents();

        this.lid = row[19].getContents();

        this.seid = row[20].getContents();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }

    public String getTitleRank() {
        return titleRank;
    }

    public void setTitleRank(String titleRank) {
        this.titleRank = titleRank;
    }

    public String getEvaClass() {
        return evaClass;
    }

    public void setEvaClass(String evaClass) {
        this.evaClass = evaClass;
    }

    public String getEvaType() {
        return evaType;
    }

    public void setEvaType(String evaType) {
        this.evaType = evaType;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getSeid() {
        return seid;
    }

    public void setSeid(String seid) {
        this.seid = seid;
    }
}
