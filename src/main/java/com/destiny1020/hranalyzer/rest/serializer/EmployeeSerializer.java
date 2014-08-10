package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EmployeeSerializer extends JsonSerializer<Employee> {

    @Override
    public void serialize(Employee employee, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        System.out.println("agent x");

        // output the custom Json
        generator.writeStartObject();

        // the type
        generator.writeFieldName("eid");
        generator.writeString(employee.getEid());

        // the full name
        generator.writeFieldName("name");
        generator.writeString(employee.getName());

        // end tag
        generator.writeEndObject();

    }
}
