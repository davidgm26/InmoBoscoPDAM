package com.salesianostriana.pdam.inmoboscoapi.exception;

public class DniInUseException extends RuntimeException{

    public DniInUseException() {
        super(" The dni entered already exists");
    }


}
