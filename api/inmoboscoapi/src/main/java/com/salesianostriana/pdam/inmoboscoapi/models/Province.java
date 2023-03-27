package com.salesianostriana.pdam.inmoboscoapi.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
