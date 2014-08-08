package com.destiny1020.hranalyzer.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Term;

public class DataConnectionTest extends DataBaseConfigBase {

    @Test
    public void testConn() {
        List<Term> terms = em.createQuery("select term from Term term",
                Term.class).getResultList();

        Assert.assertEquals(2, terms.size());
    }
}
