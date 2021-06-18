package com.emcove.rest.api.Core.utilities;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NoBadWordValidator.class)
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoBadWord {
    String message() default "Se ha detectado palabras inapropiadas.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
