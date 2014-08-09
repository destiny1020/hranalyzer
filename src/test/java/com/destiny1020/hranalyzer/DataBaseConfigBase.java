package com.destiny1020.hranalyzer;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.util.StandardizeString;

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

    // group related below
    private Map<String, Group> groupCache = new HashMap<String, Group>();

    protected Group getGroupByName(String name) {
        name = StandardizeString.standardize(name);

        if (StringUtils.isNotEmpty(name)) {
            Group cachedGroup = groupCache.get(name);
            if (cachedGroup != null) {
                return cachedGroup;
            }

            Group g = getGroupByNameCore(name);
            groupCache.put(name, g);

            return g;
        } else {
            return null;
        }

    }

    private Group getGroupByNameCore(String name) {
        return em
                .createQuery("select g from Group g where g.name = :name",
                        Group.class).setParameter("name", name)
                .getSingleResult();
    }

    // department related below
    private Map<String, Department> departmentCache = new HashMap<String, Department>();

    protected Department getDepartmentByName(String name) {
        name = StandardizeString.standardize(name);

        if (StringUtils.isNotEmpty(name)) {
            Department cachedDept = departmentCache.get(name);
            if (cachedDept != null) {
                return cachedDept;
            }

            Department dept = getDepartmentByNameCore(name);
            departmentCache.put(name, dept);

            return dept;
        } else {
            return null;
        }
    }

    private Department getDepartmentByNameCore(String name) {
        return em
                .createQuery("select d from Department d where d.name = :name",
                        Department.class).setParameter("name", name)
                .getSingleResult();
    }

    // division related below
    private Map<String, Division> divisionCache = new HashMap<String, Division>();

    protected Division getDivisionByName(String name) {
        name = StandardizeString.standardize(name);

        if (StringUtils.isNotEmpty(name)) {
            Division cachedDiv = divisionCache.get(name);
            if (cachedDiv != null) {
                return cachedDiv;
            }

            Division div = getDivisionByNameCore(name);
            divisionCache.put(name, div);

            return div;
        } else {
            return null;
        }
    }

    private Division getDivisionByNameCore(String name) {
        return em
                .createQuery("select d from Division d where d.name = :name",
                        Division.class).setParameter("name", name)
                .getSingleResult();
    }
}
