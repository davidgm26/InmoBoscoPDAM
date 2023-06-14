package com.salesianostriana.pdam.inmoboscoapi.user.validation.validator;

import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.PasswordsMatch;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch,Object>{

    private String passwordField;

    private String verifyPasswordField;


    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        passwordField = constraintAnnotation.passwordField();
        verifyPasswordField = constraintAnnotation.verifyPasswordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {

        Object fieldValue = PropertyAccessorFactory
                .forBeanPropertyAccess(o).getPropertyValue(passwordField);
        Object fieldMatchValue = PropertyAccessorFactory
                .forBeanPropertyAccess(o).getPropertyValue(verifyPasswordField);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }

}


