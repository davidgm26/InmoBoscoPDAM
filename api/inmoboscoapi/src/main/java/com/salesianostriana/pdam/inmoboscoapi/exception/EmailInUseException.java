package com.salesianostriana.pdam.inmoboscoapi.exception;

public class EmailInUseException extends RuntimeException{

    public EmailInUseException() {
        super(" The email entered already exists");
    }


}
