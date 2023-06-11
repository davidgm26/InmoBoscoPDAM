package com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation;

import com.salesianostriana.pdam.inmoboscoapi.user.validation.validator.PasswordsMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Documented
public @interface PasswordsMatch {

    String message () default "The passwords do not match.";

    Class <?> [] groups() default {};

    Class <? extends Payload> [] payload() default {};

    String passwordField();

    String verifyPasswordField();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordsMatch[] value();
    }

}
