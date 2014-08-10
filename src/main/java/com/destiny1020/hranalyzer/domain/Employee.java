package com.destiny1020.hranalyzer.domain;

import java.util.Arrays;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({ @NamedQuery(name = Employee.FIND_EMPLOYEE_BY_EID, query = Employee.FIND_EMPLOYEE_BY_EID_SQL) })
//@JsonSerialize(using = EmployeeSerializer.class)
//@JsonIgnoreProperties(value = { "records" })
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

    @Column(name = "LAST_NAME", nullable = true)
    private String lastName;

    @Column(name = "FIRST_NAME", nullable = true)
    private String firstName;

    @Column(name = "LAST_NAME2", nullable = true)
    private String lastName2;

    @Column(name = "FIRST_NAME2", nullable = true)
    private String firstName2;

    @Column(name = "LID", nullable = true)
    private String lid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTER_TERM_ID")
    private Term beginTerm;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENT_RECORD_ID", nullable = true)
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

        // assign lastnames and firstnames
        String[] names = this.name.split(" ");
        System.out.println(Arrays.toString(names));
        if (names.length >= 2) {
            this.lastName = names[0];
            this.firstName = names[names.length - 1];
        }
        this.name = this.name.replaceAll(" +", "");

        if (StringUtils.isNotEmpty(this.name2)) {
            names = this.name2.split(" ");
            if (names.length >= 2) {
                this.lastName2 = names[0];
                this.firstName2 = names[names.length - 1];
            }
            this.name2 = this.name2.replaceAll(" +", "");
        }
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

    @JsonIgnore
    public Record getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Record currentRecord) {
        this.currentRecord = currentRecord;
    }

    @JsonIgnore
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

    @JsonIgnore
    public Term getBeginTerm() {
        return beginTerm;
    }

    public void setBeginTerm(Term beginTerm) {
        this.beginTerm = beginTerm;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

}
