package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.org.Department;
import com.destiny1020.hranalyzer.domain.org.Group;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DepartmentSerializer extends JsonSerializer<Department> {

    @Override
    public void serialize(Department department, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // current department name
        generator.writeFieldName("department");
        generator.writeString(department.getName());

        // current department division
        generator.writeFieldName("division");
        generator.writeString(department.getDivision().getName());

        // current department's groups
        generator.writeFieldName("groups");
        generator.writeStartArray();
        for (Group group : department.getGroups()) {
            generator.writeString(group.getName());
        }
        generator.writeEndArray();

        // end tag
        generator.writeEndObject();
    }

}
