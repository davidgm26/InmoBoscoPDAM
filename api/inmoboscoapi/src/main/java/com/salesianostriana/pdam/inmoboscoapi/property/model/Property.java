package com.salesianostriana.pdam.inmoboscoapi.property.model;

import com.salesianostriana.pdam.inmoboscoapi.city.model.City;
import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import com.salesianostriana.pdam.inmoboscoapi.property.Type;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;

import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String lat;
    private String lon;
    private String name;
    private String title;

    @Builder.Default
    private int totalMeetBooking = 0;

    private double price;
    private ArrayList<String> img = new ArrayList<>();
    private double m2;

    @Column(columnDefinition="TEXT", length = 1000)
    private String description;
    private int totalBedRooms;

    private int totalBaths;
    private int totalVisits;

    @ManyToOne
    @JoinColumn(name = "type_id",foreignKey = @ForeignKey(name = "PROPERTY_TYPE") )
    private Type propertyType;

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_PROPERTY_CITY"))
    private City city;

    @ManyToOne
    private Owner owner;


}
