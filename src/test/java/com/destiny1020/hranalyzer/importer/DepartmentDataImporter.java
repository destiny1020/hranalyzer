package com.destiny1020.hranalyzer.importer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jxl.read.biff.BiffException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class DepartmentDataImporter extends DataBaseConfigBase {

    @Test
    public void importDepartment201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importDepartmentCore(reader, term201312);
    }

    @Test
    public void importDepartment201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importDepartmentCore(reader, term201406);
    }

    private void importDepartmentCore(XLSReader reader, Term term) {
        Department department = null;
        Set<String> existDepartments = new HashSet<String>();
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            department = new Department(reader.getRow(idx), term);

            // try to get the division by name
            if (existDepartments.contains(department.getName())) {
                continue;
            }

            Long count = em
                    .createQuery(
                            "select count(d) from Department d where d.name = :name",
                            Long.class)
                    .setParameter("name", department.getName())
                    .getSingleResult();

            if (count == 0 && StringUtils.isNotEmpty(department.getName())) {
                em.persist(department);
            }
            existDepartments.add(department.getName());
        }
    }
}
