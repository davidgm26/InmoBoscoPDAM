package com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation;

import com.salesianostriana.pdam.inmoboscoapi.user.validation.validator.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Documented
public @interface UniqueUsername {

    String message() default "This username already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
