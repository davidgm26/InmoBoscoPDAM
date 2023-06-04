package com.salesianostriana.pdam.inmoboscoapi.exception;

public class NotLoggedUser extends RuntimeException {

    public NotLoggedUser(){
        super ("You need log in to access to this resource");
    }

}
