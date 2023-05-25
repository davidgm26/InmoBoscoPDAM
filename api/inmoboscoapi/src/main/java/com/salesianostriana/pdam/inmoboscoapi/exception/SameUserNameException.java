package com.salesianostriana.pdam.inmoboscoapi.exception;

public class SameUserNameException extends RuntimeException {

    public SameUserNameException(){
        super("Ya hay un usuario registrado con ese nombre de usario");
    }

}
