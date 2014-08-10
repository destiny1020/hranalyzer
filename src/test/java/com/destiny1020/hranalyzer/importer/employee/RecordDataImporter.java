package com.destiny1020.hranalyzer.importer.employee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Employee;
import com.destiny1020.hranalyzer.domain.Record;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.destiny1020.hranalyzer.util.StandardizeString;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class RecordDataImporter extends DataBaseConfigBase {

    @Test
    public void importRecords() throws BiffException, IOException {
        importRecords201312();
        importRecords201406();
    }

    private void importRecords201312() throws BiffException, IOException {
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importRecordCore(reader, term201312);
    }

    private void importRecords201406() throws BiffException, IOException {
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importRecordCore(reader, term201406);
    }

    private void importRecordCore(XLSReader reader, Term term) {
        HRElement element = null;
        Record record = null;
        Employee employee, supervisor = null;
        Division division = null;
        Department department = null;
        Group group = null;
        Team team = null;
        String orgName = null;
        TitleClass titleClass = null;
        TitleRank titleRank = null;
        int evaluationClass, evaluationType;

        // cache the title rank lists, title class id -> title rank name -> title rank instance
        Map<Long, Map<String, TitleRank>> cacheTitleRanks = new HashMap<Long, Map<String, TitleRank>>();
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            System.out.println("start: " + idx);
            element = reader.getRow(idx);

            // employee
            employee = getEmployeeByEid(element.getEid());

            // division
            division = getDivisionByName(element.getDivision());

            // department
            department = getDepartmentByName(element.getDepartment());

            // group
            group = getGroupByName(element.getGroup());

            // team
            team = getTeamByName(element.getTeam());

            // org name
            orgName = StandardizeString.standardize(element.getOrgName());

            // title class
            titleClass = getTitleClassByName(element.getTitleClass());

            // title rank
            titleRank = getTitleRank(titleClass, cacheTitleRanks,
                    element.getTitleRank());

            // evaluation class
            evaluationClass = Integer.parseInt(element.getEvaClass());

            // evaluation type
            evaluationType = Integer.parseInt(element.getEvaType());

            // supervisor
            supervisor = getEmployeeByEid(element.getSeid());

            record = new Record(term, employee, division, department, group,
                    team, orgName, titleClass, titleRank, evaluationClass,
                    evaluationType, supervisor);

            employee.setCurrentRecord(record);
            em.persist(record);
            em.merge(employee);
        }
    }

    private TitleRank getTitleRank(TitleClass titleClass,
            Map<Long, Map<String, TitleRank>> cacheTitleRanks,
            String titleRankName) {
        if (titleClass == null) {
            return null;
        }

        if (cacheTitleRanks.get(titleClass.getId()) == null) {
            Map<String, TitleRank> innerCache = new HashMap<String, TitleRank>();
            for (TitleRank rank : titleClass.getTitleRanks()) {
                innerCache.put(rank.getName(), rank);
            }

            cacheTitleRanks.put(titleClass.getId(), innerCache);
        }

        return cacheTitleRanks.get(titleClass.getId()).get(titleRankName);
    }
}
