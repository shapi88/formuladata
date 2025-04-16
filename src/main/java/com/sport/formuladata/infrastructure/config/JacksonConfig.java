package com.sport.formuladata.infrastructure.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // Handles "2023-02-23T07:00:00+00:00"
        builder.deserializers(new LocalDateTimeDeserializer(formatter));
        builder.serializers(new LocalDateTimeSerializer(formatter));
        return builder;
    }
}