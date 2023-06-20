package com.salesianostriana.pdam.inmoboscoapi.exception;

public class NotFoundPropertyTypeException extends Exception {

    public NotFoundPropertyTypeException(String msg) {
        super(msg);
    }

    public NotFoundPropertyTypeException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
