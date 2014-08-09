package com.destiny1020.hranalyzer;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.destiny1020.hranalyzer.domain.Employee;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.domain.rank.TitleClass;
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

    // team related below
    private Map<String, Team> teamCache = new HashMap<String, Team>();

    protected Team getTeamByName(String name) {
        name = StandardizeString.standardize(name);

        if (StringUtils.isNotEmpty(name)) {
            Team cachedTeam = teamCache.get(name);
            if (cachedTeam != null) {
                return cachedTeam;
            }

            Team team = getTeamByNameCore(name);
            teamCache.put(name, team);

            return team;
        } else {
            return null;
        }

    }

    private Team getTeamByNameCore(String name) {
        return em
                .createQuery("select t from Team t where t.name = :name",
                        Team.class).setParameter("name", name)
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

    // title class related below
    private Map<String, TitleClass> titleClassCache = new HashMap<String, TitleClass>();

    protected TitleClass getTitleClassByName(String name) {
        name = StandardizeString.standardize(name);

        if (StringUtils.isNotEmpty(name)) {
            TitleClass cachedTitleClass = titleClassCache.get(name);
            if (cachedTitleClass != null) {
                return cachedTitleClass;
            }

            TitleClass tc = getTitleClassByNameCore(name);
            titleClassCache.put(name, tc);

            return tc;
        } else {
            return null;
        }
    }

    private TitleClass getTitleClassByNameCore(String name) {
        TitleClass titleClass = null;
        try {
            titleClass = em
                    .createQuery(
                            "select tc from TitleClass tc where tc.name = :name",
                            TitleClass.class).setParameter("name", name)
                    .getSingleResult();

        } catch (NoResultException nre) {
            titleClass = null;
        }

        return titleClass;
    }

    // employee class related below
    protected Employee getEmployeeByEid(String eid) {
        if (StringUtils.isEmpty(eid)) {
            return null;
        }

        Employee targetEmployee = null;
        try {
            targetEmployee = em
                    .createNamedQuery(Employee.FIND_EMPLOYEE_BY_EID,
                            Employee.class).setParameter("eid", eid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            targetEmployee = null;
        }

        return targetEmployee;
    }
}
