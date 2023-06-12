package com.salesianostriana.pdam.inmoboscoapi.exception;

public class NotOwnerException extends RuntimeException{

    public NotOwnerException(String username) {
        super(String.format("The user %s is not owner.",username));
    }
}
