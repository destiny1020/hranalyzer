package com.destiny1020.hranalyzer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.destiny1020.hranalyzer.domain.Term;

public class DataBaseConfigBase {

    private static final Logger LOGGER = LogManager
            .getLogger(DataBaseConfigBase.class);

    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    protected EntityTransaction tx;

    @BeforeClass
    public static void init() {
        emf = Persistence.createEntityManagerFactory("hranalyzer_local");
        em = emf.createEntityManager();
    }

    @Before
    public void beginTransaction() {
        tx = em.getTransaction();
        tx.begin();

        LOGGER.info("Tx starts....");
    }

    @After
    public void rollbackTransaction() {
        if (tx.isActive()) {
            LOGGER.info("Tx commits....");
            tx.commit();
        }
    }

    @AfterClass
    public static void clear() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    protected Term getTermByName(String name) {
        return em
                .createQuery(
                        "select term from Term term where term.name = :name",
                        Term.class).setParameter("name", name)
                .getSingleResult();
    }
}
