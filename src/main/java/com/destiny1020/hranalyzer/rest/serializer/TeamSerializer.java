package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.org.Team;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TeamSerializer extends JsonSerializer<Team> {

    @Override
    public void serialize(Team team, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // current team name
        generator.writeFieldName("name");
        generator.writeString(team.getName());

        // current team's group name
        generator.writeFieldName("group");
        generator.writeString(team.getGroup().getName());

        // end tag
        generator.writeEndObject();
    }

}
