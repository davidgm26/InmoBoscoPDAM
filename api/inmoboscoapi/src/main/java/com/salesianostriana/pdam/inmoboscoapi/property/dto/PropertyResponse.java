package com.salesianostriana.pdam.inmoboscoapi.property.dto;

import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PropertyResponse {

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

    public static PropertyResponse convertPropertyResponseFromProperty(Property p){
        return PropertyResponse.builder()
                .city(p.getCity().getName())
                .lat(p.getLat())
                .lon(p.getLon())
                .name(p.getName())
                .title(p.getTitle())
                .m2(p.getM2())
                .description(p.getDescription())
                .totalBaths(p.getTotalBaths())
                .totalBedRooms(p.getTotalBedRooms())
                .propertyType(p.getPropertyType().getType())
                .build();

    }

}
