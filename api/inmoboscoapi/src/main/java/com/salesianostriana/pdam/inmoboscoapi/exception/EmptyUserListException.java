package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyUserListException extends EntityNotFoundException {

    public EmptyUserListException(){
        super("No se han encontrado usuarios");
    }

}
