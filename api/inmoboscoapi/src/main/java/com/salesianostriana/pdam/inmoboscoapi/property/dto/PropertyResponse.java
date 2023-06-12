package com.salesianostriana.pdam.inmoboscoapi.property.dto;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PropertyResponse {

    protected long id;
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
    private String owner;

    public static PropertyResponse convertPropertyResponseFromProperty(Property p) {
        PropertyResponse.PropertyResponseBuilder builder = PropertyResponse.builder()
                .id(p.getId())
                .city(p.getCity().getName())
                .lat(p.getLat())
                .lon(p.getLon())
                .name(p.getName())
                .title(p.getTitle())
                .m2(p.getM2())
                .description(p.getDescription())
                .totalBaths(p.getTotalBaths())
                .totalBedRooms(p.getTotalBedRooms())
                .propertyType(p.getPropertyType().getType());

        if (p.getOwner() != null) {
            builder.owner(p.getOwner().getUsername());
        } else {
            builder.owner("");
        }

        return builder.build();
    }


}
