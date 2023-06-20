package com.salesianostriana.pdam.inmoboscoapi.exception;

public class PropertyAlredyInListException extends RuntimeException {

    public PropertyAlredyInListException(Long id) {
        super(String.format("The property with id: %d is already in your favourite list", id));
    }

}
