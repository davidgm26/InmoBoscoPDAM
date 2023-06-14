package com.salesianostriana.pdam.inmoboscoapi.exception;

public class UserHaveProperties extends RuntimeException{

    public UserHaveProperties() {
        super(String.format("This user have any properties in their possession and you cant delete him"));
    }
}
