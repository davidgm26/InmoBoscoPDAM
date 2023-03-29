package com.salesianostriana.pdam.inmoboscoapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@DiscriminatorValue("W")
@SuperBuilder
public class Workers extends User{

    private String workerID;

}
