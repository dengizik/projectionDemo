package com.lbs.data.demo.topic.model.adapter;

import com.lbs.data.demo.topic.model.entity.LbsSafe;
import com.lbs.data.demo.topic.model.entity.LbsBankAccount;
import gherkin.deps.com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Title :
 * Description :
 * Copyright : Copyright (c) 2016
 * Company : LBS
 * Created on 23.12.2016
 * @author Kemal.Eroglu
 * @version 1.0
 */

public class LbsAccountAdapter<LbsAccount> implements JsonSerializer<LbsAccount>, JsonDeserializer<LbsAccount> {

    @Override
    public LbsAccount deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (!jsonObject.has("type")) {
            return null;
        }
        String typeofT = jsonObject.get("type").getAsString();
        try {
            return jsonDeserializationContext.deserialize(jsonElement, Class.forName("com.lbs.soho.domain.soho." + typeofT));
        }
        catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + typeofT, cnfe);
        }
    }

    @Override
    public JsonElement serialize(LbsAccount lbsAccount, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        if (lbsAccount instanceof LbsSafe) {
            result = jsonSerializationContext.serialize(lbsAccount, LbsSafe.class).getAsJsonObject();
        }
        else if (lbsAccount instanceof LbsBankAccount) {
            result = jsonSerializationContext.serialize(lbsAccount, LbsBankAccount.class).getAsJsonObject();
        }
        result.add("type", new JsonPrimitive(lbsAccount.getClass().getSimpleName()));
        return result;
    }
}

