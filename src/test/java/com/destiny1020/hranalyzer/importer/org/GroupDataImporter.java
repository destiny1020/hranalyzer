package com.destiny1020.hranalyzer.importer.org;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jxl.read.biff.BiffException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class GroupDataImporter extends DataBaseConfigBase {

    @Test
    public void importGroupData() throws BiffException, IOException {
        importGroup201312();
        importGroup201406();
    }
    
    public void importGroup201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importGroupCore(reader, term201312);
    }

    public void importGroup201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importGroupCore(reader, term201406);
    }

    private void importGroupCore(XLSReader reader, Term term) {
        Group group = null;
        Department department = null;
        HRElement element = null;
        Set<String> existGroups = new HashSet<String>();
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            element = reader.getRow(idx);
            group = new Group(element, term);

            // try to get the group by name
            if (existGroups.contains(group.getName())) {
                continue;
            }

            Long count = em
                    .createQuery(
                            "select count(g) from Group g where g.name = :name",
                            Long.class).setParameter("name", group.getName())
                    .getSingleResult();

            if (count == 0 && StringUtils.isNotEmpty(group.getName())) {
                // find the department info
                department = getDepartmentByName(element.getDepartment());
                if (department != null) {
                    group.setDepartment(department);
                }

                em.persist(group);
            }
            existGroups.add(group.getName());
        }
    }
}
