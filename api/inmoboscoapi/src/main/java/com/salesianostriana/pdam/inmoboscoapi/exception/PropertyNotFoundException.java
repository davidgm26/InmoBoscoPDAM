package com.salesianostriana.pdam.inmoboscoapi.exception;

import javax.persistence.EntityNotFoundException;

public class PropertyNotFoundException extends EntityNotFoundException {


    public PropertyNotFoundException(){
        super("No se han encontrado inmbuebles");
    }

    public PropertyNotFoundException(Long id){
        super("No se han encontrado inmbuebles con el id: " + id);
    }

}
