package com.salesianostriana.pdam.inmoboscoapi.user.validation.validator;

import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniqueEmail;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    private final UserService userService;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsByEmail(s);
    }
}
