package com.salesianostriana.pdam.inmoboscoapi.exception;


public class UsernameInUseException extends RuntimeException{

    public UsernameInUseException() {
        super("This username alredy exists");
    }
}
