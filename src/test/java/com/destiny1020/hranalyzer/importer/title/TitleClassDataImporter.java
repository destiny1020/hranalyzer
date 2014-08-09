package com.destiny1020.hranalyzer.importer.title;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jxl.read.biff.BiffException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class TitleClassDataImporter extends DataBaseConfigBase {

    private Set<String> existTitleClasses = new HashSet<String>();

    @Test
    public void importTitleClassData() throws BiffException, IOException {
        importTitleClass201312();
        importTitleClass201406();
    }

    public void importTitleClass201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importTitleClassCore(reader, term201312);
    }

    public void importTitleClass201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importTitleClassCore(reader, term201406);
    }

    private void importTitleClassCore(XLSReader reader, Term term) {
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            TitleClass tc = new TitleClass(reader.getRow(idx), term);

            if (existTitleClasses.contains(tc.getName())) {
                continue;
            }

            // persist
            if (StringUtils.isNotEmpty(tc.getName())) {
                em.persist(tc);
            }

            existTitleClasses.add(tc.getName());
        }
    }
}
