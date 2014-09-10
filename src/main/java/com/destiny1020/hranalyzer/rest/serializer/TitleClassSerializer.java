package com.destiny1020.hranalyzer.rest.serializer;

import java.io.IOException;
import java.util.List;

import com.destiny1020.hranalyzer.domain.rank.TitleClass;
import com.destiny1020.hranalyzer.domain.rank.TitleRank;
import com.destiny1020.hranalyzer.rest.dto.TitleRankDto;
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

        // current title class id
        generator.writeFieldName("id");
        generator.writeNumber(titleClass.getId());

        // current title class name
        generator.writeFieldName("titleClass");
        generator.writeString(titleClass.getName());

        List<TitleRank> titleRanks = titleClass.getTitleRanks();

        // current title class's ranks' number
        generator.writeFieldName("titleRanksCount");
        generator.writeNumber(titleRanks.size());

        // current title class's ranks
        generator.writeFieldName("ranks");
        generator.writeStartArray();
        for (TitleRank rank : titleClass.getTitleRanks()) {
            generator.writeObject(new TitleRankDto(rank));
        }
        generator.writeEndArray();

        // end tag
        generator.writeEndObject();
    }

}
