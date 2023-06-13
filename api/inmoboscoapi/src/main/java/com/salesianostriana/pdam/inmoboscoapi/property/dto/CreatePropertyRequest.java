package com.salesianostriana.pdam.inmoboscoapi.property.dto;

import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor @NoArgsConstructor
@Data
public class CreatePropertyRequest {

    private String lat;
    private String lon;
    private String name;
    private String title;
    private double price;
    private double m2;
    private String description;
    private int totalBedRooms;
    private int totalBaths;
    private String propertyType;
    private String city;


}
