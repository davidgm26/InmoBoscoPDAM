package com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation;

import com.salesianostriana.pdam.inmoboscoapi.user.validation.validator.UniquePhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneNumberValidator.class)
@Documented

public @interface UniquePhoneNumber {

    String message() default "This phoneNumber already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

