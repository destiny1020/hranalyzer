package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.Employee;
import com.destiny1020.hranalyzer.domain.Record;
import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.destiny1020.hranalyzer.rest.dto.DepartmentDto;
import com.destiny1020.hranalyzer.rest.dto.DivisionDto;
import com.destiny1020.hranalyzer.rest.dto.GroupDto;
import com.destiny1020.hranalyzer.rest.dto.SupervisorDto;
import com.destiny1020.hranalyzer.rest.dto.TeamDto;
import com.destiny1020.hranalyzer.rest.dto.TitleClassDto;
import com.destiny1020.hranalyzer.rest.dto.TitleRankDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class RecordSerializer extends JsonSerializer<Record> {

    @Override
    public void serialize(Record record, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // the term for current record
        generator.writeFieldName("term");
        generator.writeObject(record.getTerm());

        // current division
        Division division = record.getDivision();
        if (division != null) {
            generator.writeFieldName("division");
            generator.writeObject(new DivisionDto(record.getDivision()));
        }

        // current department
        Department department = record.getDepartment();
        if (department != null) {
            generator.writeFieldName("department");
            generator.writeObject(new DepartmentDto(record.getDepartment()));
        }

        // current group
        Group group = record.getGroup();
        if (group != null) {
            generator.writeFieldName("group");
            generator.writeObject(new GroupDto(record.getGroup()));
        }

        // current team
        Team team = record.getTeam();
        if (team != null) {
            generator.writeFieldName("team");
            generator.writeObject(new TeamDto(record.getTeam()));
        }

        // current title class
        TitleClass titleClass = record.getTitleClass();
        if (titleClass != null) {
            generator.writeFieldName("titleClass");
            generator.writeObject(new TitleClassDto(record.getTitleClass()));
        }

        // current title rank
        TitleRank titleRank = record.getTitleRank();
        if (titleRank != null) {
            generator.writeFieldName("titleRank");
            generator.writeObject(new TitleRankDto(record.getTitleRank()));
        }

        // current supervisor
        Employee supervisor = record.getSupervisor();
        if (supervisor != null) {
            generator.writeFieldName("supervisor");
            generator.writeObject(new SupervisorDto(record.getSupervisor()));
        }

        // current organization official name
        generator.writeFieldName("orgName");
        generator.writeString(record.getOrgName());

        // end tag
        generator.writeEndObject();

    }
}
