package com.destiny1020.hranalyzer.domain.org;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.destiny1020.hranalyzer.domain.Term;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OrganizationBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK", updatable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTER_TERM_ID")
    protected Term beginTerm;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Term getBeginTerm() {
        return beginTerm;
    }

    public void setBeginTerm(Term beginTerm) {
        this.beginTerm = beginTerm;
    }

}
