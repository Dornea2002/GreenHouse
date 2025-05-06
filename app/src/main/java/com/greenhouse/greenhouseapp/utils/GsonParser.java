package com.greenhouse.greenhouseapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.greenhouse.greenhouseapp.models.Plant;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GsonParser {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public static ArrayList<Plant> fromGsonToList(String json) {
        Type listType = new TypeToken<ArrayList<Plant>>() {}.getType();
        return gson.fromJson(json, listType);
    }
    public static Plant fromGsonToObject(String json) {
        return gson.fromJson(json, Plant.class);
    }
}