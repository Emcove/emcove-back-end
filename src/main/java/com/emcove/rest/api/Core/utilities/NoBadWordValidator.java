package com.emcove.rest.api.Core.utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoBadWordValidator implements ConstraintValidator<NoBadWord,String> {
    private Set<String> badWords;

    @Override
    public void initialize(NoBadWord constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        badWords = Stream.of("falopa","merca","droga","marihuana","cocaina","heroina").collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return true;
        System.out.println("validation value:" + value);
        return !badWords.stream().anyMatch(badWord -> value.toLowerCase().contains(badWord));
    }
}
