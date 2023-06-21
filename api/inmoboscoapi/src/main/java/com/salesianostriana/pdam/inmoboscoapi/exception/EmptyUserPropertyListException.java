package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyUserPropertyListException extends EntityNotFoundException {

    public EmptyUserPropertyListException(String username) {
        super(String.format("The user %s does not have any properties in their possession.",username));
    }
}
