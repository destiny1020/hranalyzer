package com.destiny1020.hranalyzer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TERM")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK", updatable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

}
