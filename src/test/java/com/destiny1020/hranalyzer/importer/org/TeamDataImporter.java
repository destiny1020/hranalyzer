package com.destiny1020.hranalyzer.importer.org;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jxl.read.biff.BiffException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.destiny1020.hranalyzer.DataBaseConfigBase;
import com.destiny1020.hranalyzer.domain.Term;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.xls.HRElement;
import com.destiny1020.hranalyzer.xls.XLSReader;

public class TeamDataImporter extends DataBaseConfigBase {

    @Test
    public void importTeamData() throws BiffException, IOException {
        importTeam201312();
        importTeam201406();
    }

    public void importTeam201312() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201312.xls");
        Term term201312 = getTermByName("201312");

        importTeamCore(reader, term201312);
    }

    public void importTeam201406() throws BiffException, IOException {
        // prepare the data from source
        XLSReader reader = new XLSReader("data/201406.xls");
        Term term201406 = getTermByName("201406");

        importTeamCore(reader, term201406);
    }

    private void importTeamCore(XLSReader reader, Term term) {
        Team team = null;
        Group group = null;
        HRElement element = null;
        Set<String> existTeams = new HashSet<String>();
        for (int idx = 1; idx < reader.getTotalRows(); idx++) {
            element = reader.getRow(idx);
            team = new Team(element, term);

            // try to get the team by name
            if (existTeams.contains(team.getName())) {
                continue;
            }

            Long count = em
                    .createQuery(
                            "select count(t) from Team t where t.name = :name",
                            Long.class).setParameter("name", team.getName())
                    .getSingleResult();

            if (count == 0 && StringUtils.isNotEmpty(team.getName())) {
                // find the group info
                group = getGroupByName(element.getGroup());
                if (group != null) {
                    team.setGroup(group);
                }

                em.persist(team);
            }
            existTeams.add(team.getName());
        }
    }
}
