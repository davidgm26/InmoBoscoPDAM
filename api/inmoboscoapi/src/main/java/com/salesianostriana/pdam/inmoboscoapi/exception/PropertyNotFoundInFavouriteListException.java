package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class PropertyNotFoundInFavouriteListException extends EntityNotFoundException {

    public PropertyNotFoundInFavouriteListException(Long id) {
        super(String.format("The property with id: %d is not in your favourite list", id));
    }


}
