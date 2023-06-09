package com.salesianostriana.pdam.inmoboscoapi.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException(){
        super ("Those passwords didn't match. Try again");
    }

}
