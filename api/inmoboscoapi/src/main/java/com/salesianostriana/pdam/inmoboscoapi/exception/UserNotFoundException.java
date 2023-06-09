package com.salesianostriana.pdam.inmoboscoapi.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(UUID id){
        super(String.format("No se ha encontrado usuario con el id", id.toString()));
    }
}
