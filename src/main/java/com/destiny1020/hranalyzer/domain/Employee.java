package com.destiny1020.hranalyzer.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;

@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({ @NamedQuery(name = Employee.FIND_EMPLOYEE_BY_EID, query = Employee.FIND_EMPLOYEE_BY_EID_SQL) })
public class Employee {

    public static final String FIND_EMPLOYEE_BY_EID = "findEmployeeByEid";
    public static final String FIND_EMPLOYEE_BY_EID_SQL = "select e from Employee e where e.eid = :eid";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK", updatable = false)
    private Long id;

    @Column(name = "EID", nullable = false, unique = true)
    private String eid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "NAME2", nullable = true)
    private String name2;

    @Column(name = "LID", nullable = true)
    private String lid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTER_TERM_ID")
    private Term beginTerm;

    @Transient
    private Record currentRecord;

    @OneToMany(mappedBy = "employee")
    private List<Record> records;

    public Employee() {

    }

    public Employee(HRElement element, Term beginTerm) {
        this.eid = element.getEid().trim();
        this.name = StandardizeString.standardize(element.getName());
        this.name2 = StandardizeString.standardize(element.getName2());
        this.lid = element.getLid().trim();

        this.beginTerm = beginTerm;
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

    public Record getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Record currentRecord) {
        this.currentRecord = currentRecord;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Long getId() {
        return id;
    }

    public String getEid() {
        return eid;
    }

    public String getLid() {
        return lid;
    }

    public Term getBeginTerm() {
        return beginTerm;
    }

    public void setBeginTerm(Term beginTerm) {
        this.beginTerm = beginTerm;
    }

}
