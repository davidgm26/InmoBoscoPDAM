package com.salesianostriana.pdam.inmoboscoapi.invoice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date deadline;

    private boolean paid;

    private LocalDate creationDate;
}
