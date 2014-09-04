package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Team;
import com.destiny1020.hranalyzer.rest.dto.DepartmentDto;
import com.destiny1020.hranalyzer.rest.dto.TeamDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GroupSerializer extends JsonSerializer<Group> {

    @Override
    public void serialize(Group group, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // current division id
        generator.writeFieldName("id");
        generator.writeNumber(group.getId());

        // current group name
        generator.writeFieldName("name");
        generator.writeString(group.getName());

        // current group's department
        generator.writeFieldName("department");
        generator.writeObject(new DepartmentDto(group.getDepartment()));

        // current group's teams
        generator.writeFieldName("teams");
        generator.writeStartArray();
        for (Team team : group.getTeams()) {
            generator.writeObject(new TeamDto(team));
        }
        generator.writeEndArray();

        // end tag
        generator.writeEndObject();
    }

}
