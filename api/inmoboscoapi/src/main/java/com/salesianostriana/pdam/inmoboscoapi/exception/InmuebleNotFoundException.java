package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class InmuebleNotFoundException extends EntityNotFoundException {


    public InmuebleNotFoundException(){
        super("No se han encontrado inmbuebles");
    }

    public InmuebleNotFoundException(Long id){
        super("No se han encontrado inmbuebles con el id: " + id);
    }

}
