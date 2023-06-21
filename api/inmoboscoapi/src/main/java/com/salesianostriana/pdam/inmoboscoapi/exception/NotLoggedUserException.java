package com.salesianostriana.pdam.inmoboscoapi.exception;

public class NotLoggedUserException extends RuntimeException {

    public NotLoggedUserException(){
        super ("You need log in to access to this resource");
    }

}
