package com.destiny1020.hranalyzer.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

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

    @Column(name = "LID", nullable = true, unique = true)
    private String lid;

    @Transient
    private Record currentRecord;

    @OneToMany(mappedBy = "employee")
    private List<Record> records;

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

}
