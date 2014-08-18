package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TitleClassSerializer extends JsonSerializer<TitleClass> {

    @Override
    public void serialize(TitleClass titleClass, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // current title class name
        generator.writeFieldName("name");
        generator.writeString(titleClass.getName());

        // current title class's ranks
        generator.writeFieldName("ranks");
        generator.writeStartArray();
        for (TitleRank rank : titleClass.getTitleRanks()) {
            generator.writeString(rank.getName());
        }
        generator.writeEndArray();

        // end tag
        generator.writeEndObject();
    }

}
