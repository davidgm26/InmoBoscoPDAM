package com.salesianostriana.pdam.inmoboscoapi.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import lombok.*;

import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date rentDate;

    private Date deadLine;

    private double monthlyPayment;

    private String type;

}
