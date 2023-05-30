package com.salesianostriana.pdam.inmoboscoapi.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor @NoArgsConstructor
@Data
public class CreatePropertyRequest {

    protected String lat;
    protected String lon;
    protected String name;
    protected String title;
    protected double price;
    protected double m2;
    protected String description;
    protected int totalBedRooms;
    protected int totalBaths;
    protected String propertyType;
    protected String city;


}
