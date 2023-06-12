package com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation;


import com.salesianostriana.pdam.inmoboscoapi.user.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "This email already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
