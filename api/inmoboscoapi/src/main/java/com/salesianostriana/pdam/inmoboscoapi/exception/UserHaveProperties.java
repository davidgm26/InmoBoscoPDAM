package com.salesianostriana.pdam.inmoboscoapi.exception;

public class UserHaveProperties extends RuntimeException{

    public UserHaveProperties(String username) {
        super(String.format("The user %s have any properties in their possession and you cant delete him",username));
    }
}
