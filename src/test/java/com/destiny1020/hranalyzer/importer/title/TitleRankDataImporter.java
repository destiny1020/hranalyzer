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
import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class TitleRankDataImporter extends DataBaseConfigBase {

    private Set<String> existTitleRanks = new HashSet<String>();

    @Test
    public void importTitleRankData() throws BiffException, IOException {
        importTitleRank201312();
        importTitleRank201406();
    }

    public void importTitleRank201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importTitleRankCore(reader, term201312);
    }

    public void importTitleRank201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importTitleRankCore(reader, term201406);
    }

    private void importTitleRankCore(XLSReader reader, Term term) {
        TitleClass tc = null;
        HRElement element = null;
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            element = reader.getRow(idx);
            TitleRank tr = new TitleRank(element, term);

            if (existTitleRanks.contains(tr.getName())) {
                continue;
            }

            Long count = em
                    .createQuery(
                            "select count(tr) from TitleRank tr where tr.name = :name",
                            Long.class).setParameter("name", tr.getName())
                    .getSingleResult();

            // persist
            if (count == 0 && StringUtils.isNotEmpty(tr.getName())) {
                // find the title class info
                tc = getTitleClassByName(element.getTitleClass());
                if (tc != null) {
                    tr.setTitleClass(tc);
                }

                em.persist(tr);
            }

            existTitleRanks.add(tr.getName());
        }
    }
}
