package com.lbs.data.demo.topic.model.entity;

import gherkin.deps.com.google.gson.JsonDeserializationContext;
import gherkin.deps.com.google.gson.JsonDeserializer;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

public class LocalDateAdapter implements JsonDeserializer {

    @Override
    public java.time.LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return java.time.LocalDate.parse(json.getAsJsonPrimitive().getAsString());
        } catch (DateTimeParseException e) {
            LocalDateTime ldt = LocalDateTime
                    .ofInstant(Instant.parse(json.getAsJsonPrimitive().getAsString()), ZoneId.of("Europe/Moscow"));
            return ldt.toLocalDate();
        } catch (IllegalStateException s) {
            return java.time.LocalDate
                    .of(json.getAsJsonObject().get("year").getAsInt(), json.getAsJsonObject().get("month").getAsInt(), json
                            .getAsJsonObject().get("day").getAsInt());
        }
    }
}

