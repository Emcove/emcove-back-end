package com.emcove.rest.api.Core.utilities;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResponseUtils {
       public static boolean validWord(String ... words){
        Set<String> badWords = Stream.of("falopa","merca","droga","marihuana","cocaina","heroina","verga","chota").collect(Collectors.toSet());
        for(String word : words){
            if(badWords.stream().anyMatch(badWord -> word.toLowerCase().contains(badWord)))
                return false;
        }
        return true;
    }
}
