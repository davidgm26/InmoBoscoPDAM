package com.salesianostriana.pdam.inmoboscoapi.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@DiscriminatorValue("W")
@SuperBuilder
public class Worker extends User{

    private String workerID;

}
