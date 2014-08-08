package com.destiny1020.hranalyzer.importer;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class DivisionDataImporter extends DataBaseConfigBase {

    @Test
    public void importDivision201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");

        Term term201312 = em.createQuery(
                "select term from Term term where term.name = '201312'",
                Term.class).getSingleResult();

        Division division = null;
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            division = new Division(reader.getRow(idx), term201312);

            //            if (division.getName().isEmpty()) {
            //                System.out.println(reader.getRow(idx));
            //            }

            // try to get the division by name
            Long count = em
                    .createQuery(
                            "select count(d) from Division d where d.name = :name",
                            Long.class)
                    .setParameter("name", division.getName()).getSingleResult();

            if (count == 0 && !division.getName().isEmpty()) {
                em.persist(division);
            }
        }
    }

    @Test
    public void importDivision201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");

        Term term201406 = em.createQuery(
                "select term from Term term where term.name = '201406'",
                Term.class).getSingleResult();

        Division division = null;
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            division = new Division(reader.getRow(idx), term201406);

            // try to get the division by name
            Long count = em
                    .createQuery(
                            "select count(d) from Division d where d.name = :name",
                            Long.class)
                    .setParameter("name", division.getName()).getSingleResult();

            if (count == 0 && !division.getName().isEmpty()) {
                em.persist(division);
            }
        }
    }

}
