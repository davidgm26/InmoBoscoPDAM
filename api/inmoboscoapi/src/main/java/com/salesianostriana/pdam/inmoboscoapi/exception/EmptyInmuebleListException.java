package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyInmuebleListException extends EntityNotFoundException {

    public EmptyInmuebleListException(){
        super("No se han encontrado inmuebles");
    }

}
