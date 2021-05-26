package com.emcove.rest.api.Core.utilities;

import com.google.gson.Gson;

import java.time.LocalDate;

public class ResponseUtils {
    public static String toJson(Object src){
        Gson gson = new Gson();

        return gson.toJson(src);
    }

    public static LocalDate getLocalDateFromString(String dateStr){
        String[] dateSplit = dateStr.split("/");

        return LocalDate.of(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[0]));
    }
}
