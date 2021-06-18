package com.emcove.rest.api.Core.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class NoBadWordTest {

    @Test
    public void streamFilterTest(){
         //List<String> badWords = new ArrayList<String>(Arrays.asList("falopa,merca,droga,marihuana,cocaina,heroina".split(",")));
         Set<String> badWords = Stream.of("falopa","merca","droga","marihuana","cocaina","heroina").collect(Collectors.toSet());
         String value = "Falopa";
         String value2 = "hola";

         System.out.println(badWords.stream().anyMatch(badWord -> value.toLowerCase().contains(badWord)));
         System.out.println(badWords.stream().anyMatch(badWord -> value2.toLowerCase().contains(badWord)));
     }
}
