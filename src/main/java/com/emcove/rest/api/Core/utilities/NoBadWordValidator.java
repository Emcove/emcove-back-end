package com.emcove.rest.api.Core.utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoBadWordValidator implements ConstraintValidator<NoBadWord,Object> {
    private Set<String> badWords;
    private Set<NoBadWordObject> content;

    @Override
    public void initialize(NoBadWord constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        content = new HashSet<NoBadWordObject>(Arrays.asList(constraintAnnotation.content()));

        badWords = Stream.of("falopa","merca","droga","marihuana","cocaina","heroina").collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if(o == null)
            return true;
        for (NoBadWordObject type : content){
            switch (type){
                case STRING:
                    if(!(o instanceof String))
                        return false;
                    String str = (String) o;
                    return !badWords.stream().anyMatch(badWord -> str.toLowerCase().contains(badWord));

                case List:
                    if(!(o instanceof List))
                        return false;
                    List<String> list = (List<String>) o;
                    for (String value : list){
                        if(badWords.stream().anyMatch(badWord -> value.toLowerCase().contains(badWord)))
                            return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
}
