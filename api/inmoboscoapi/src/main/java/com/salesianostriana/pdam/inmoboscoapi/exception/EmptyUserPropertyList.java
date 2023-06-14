package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyUserPropertyList extends EntityNotFoundException {

    public EmptyUserPropertyList(String username) {
        super(String.format("The user %s does not have any properties in their possession.",username));
    }
}
