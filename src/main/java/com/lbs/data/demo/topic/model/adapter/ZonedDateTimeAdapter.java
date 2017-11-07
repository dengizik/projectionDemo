package com.lbs.data.demo.topic.model.adapter;

import gherkin.deps.com.google.gson.JsonDeserializationContext;
import gherkin.deps.com.google.gson.JsonDeserializer;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeAdapter implements JsonDeserializer {

    @Override
    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ZonedDateTime zdt = ZonedDateTime
                .ofInstant(Instant.parse(json.getAsJsonPrimitive().getAsString()), ZoneId.of("Europe/Moscow"));
        return zdt;
    }

}