package com.salesianostriana.pdam.inmoboscoapi.exception;

public class UserHavePropertiesException extends RuntimeException{

    public UserHavePropertiesException() {
        super(String.format("This user have any properties in their possession and you cant delete him"));
    }
}
