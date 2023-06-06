package com.salesianostriana.pdam.inmoboscoapi.exception;

public class NotFoundPropertyType extends Exception {

    public NotFoundPropertyType(String msg) {
        super(msg);
    }

    public NotFoundPropertyType(String msg, Throwable causa) {
        super(msg, causa);
    }
}
