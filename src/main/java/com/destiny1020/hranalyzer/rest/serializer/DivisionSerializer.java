package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;
import java.util.List;

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

        List<Department> departments = division.getDepartments();

        // current division's departments' number
        generator.writeFieldName("departmentsCount");
        generator.writeNumber(departments.size());

        // current division's departments
        generator.writeFieldName("departments");
        generator.writeStartArray();
        for (Department department : departments) {
            generator.writeString(department.getName());
        }
        generator.writeEndArray();

        // end tag
        generator.writeEndObject();
    }

}
