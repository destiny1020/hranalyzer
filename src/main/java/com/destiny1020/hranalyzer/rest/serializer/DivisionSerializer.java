package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Division;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DivisionSerializer extends JsonSerializer<Division> {

    @Override
    public void serialize(Division division, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // current division name
        generator.writeFieldName("division");
        generator.writeString(division.getName());

        // current division's departments
        generator.writeFieldName("departments");
        generator.writeStartArray();
        for (Department department : division.getDepartments()) {
            generator.writeString(department.getName());
        }
        generator.writeEndArray();

        // end tag
        generator.writeEndObject();
    }

}
