package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;

import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TitleRankSerializer extends JsonSerializer<TitleRank> {

    @Override
    public void serialize(TitleRank titleRank, JsonGenerator generator,
            SerializerProvider provider) throws IOException,
            JsonProcessingException {

        // output the custom Json
        generator.writeStartObject();

        // current title class name
        generator.writeFieldName("name");
        generator.writeString(titleRank.getName());

        // current title rank's title class
        generator.writeFieldName("titleRank");
        generator.writeString(titleRank.getTitleClass().getName());

        // end tag
        generator.writeEndObject();
    }

}
