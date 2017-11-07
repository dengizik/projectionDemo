package com.lbs.data.demo.topic.util;


//import com.lbs.data.LbsDeletionTokenExclusionStrategyGherkin;
import com.lbs.data.demo.topic.model.adapter.DateAdapter;
import com.lbs.data.demo.topic.model.adapter.ZonedDateTimeAdapter;
import com.lbs.data.demo.topic.util.LbsAccountAdapter;
import com.lbs.data.demo.topic.model.entity.LocalDateAdapter;
import com.lbs.data.demo.topic.model.entity.LbsAccount;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Title :
 * Description :
 * Copyright : Copyright (c) 2016
 * Company : LBS
 * Created on 8.11.2016
 *
 * @author Cihan.Kandis
 * @version 1.0
 */
public class GsonUtil {

    private final static JsonParser parser = new JsonParser();
    private final static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        builder.registerTypeAdapter(Date.class, new DateAdapter());
        builder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter());
        builder.registerTypeAdapter(LbsAccount.class, new LbsAccountAdapter<>());

//        LbsDeletionTokenExclusionStrategyGherkin strategy = new LbsDeletionTokenExclusionStrategyGherkin();
//        builder.addSerializationExclusionStrategy(strategy);
//        builder.addDeserializationExclusionStrategy(strategy);

        gson = builder.create();
    }

    public static JsonObject parseJSONfromBytes(byte[] bytes) {
        String byteString = new String(bytes, StandardCharsets.UTF_8);
        return parser.parse(byteString).getAsJsonObject();
    }

    public static byte[] serializeToJSONBytes(Object serializableObject, Type type) {
        return gson.toJson(serializableObject, type).toString().getBytes(StandardCharsets.UTF_8);
    }

    public static Gson getGson() {
        return gson;
    }

    public static JsonParser getParser() {
        return parser;
    }
}
