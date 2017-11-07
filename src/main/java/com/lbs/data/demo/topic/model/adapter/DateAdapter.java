package com.lbs.data.demo.topic.model.adapter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;
import gherkin.deps.com.google.gson.JsonDeserializationContext;
import gherkin.deps.com.google.gson.JsonDeserializer;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;

/**
 * Title :
 * Description :
 * Copyright : Copyright (c) 2016
 * Company : LBS
 * Created on 26.1.2017
 *
 * @author Cihan.Kandis
 * @version 1.0
 */
public class DateAdapter implements JsonDeserializer {

    @Override
    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            Date date = null;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            try {
                String dateString = json.getAsJsonPrimitive().getAsString().replace("T", " ").replace("Z", " ");
                date = format.parse(dateString);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        catch (DateTimeParseException e) {

        }
        catch (IllegalStateException s) {

        }
        return new Date();
    }

}
