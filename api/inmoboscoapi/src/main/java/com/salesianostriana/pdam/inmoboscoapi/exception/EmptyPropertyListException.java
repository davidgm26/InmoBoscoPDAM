package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyPropertyListException extends EntityNotFoundException {

    public EmptyPropertyListException(){
        super("No se han encontrado inmuebles");
    }

}
