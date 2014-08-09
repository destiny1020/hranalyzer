package com.destiny1020.hranalyzer.importer.employee;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Employee;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class EmployeeDataImporter extends DataBaseConfigBase {

    @Test
    public void importEmployees() throws BiffException, IOException {
        importEmployees201312();
        importEmployees201406();
    }

    public void importEmployees201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importEmployeeCore(reader, term201312);
    }

    public void importEmployees201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importEmployeeCore(reader, term201406);
    }

    private void importEmployeeCore(XLSReader reader, Term term) {
        Employee employee = null;
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            employee = new Employee(reader.getRow(idx), term);

            // try to get the employee by eid
            Long count = em
                    .createQuery(
                            "select count(e) from Employee e where e.eid = :eid",
                            Long.class).setParameter("eid", employee.getEid())
                    .getSingleResult();
            if (count == 0) {
                em.persist(employee);
            }
        }
    }

}
