package com.salesianostriana.pdam.inmoboscoapi.property.model;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import com.salesianostriana.pdam.inmoboscoapi.city.model.City;
import com.salesianostriana.pdam.inmoboscoapi.property.Type;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private String img;

    private double m2;

    @Column(columnDefinition="TEXT", length = 1000)
    private String description;
    private int totalBedRooms;

    private int totalBaths;
    @Builder.Default
    private int totalVisits = 0;

    @ManyToOne
    @JoinColumn(name = "type_id",foreignKey = @ForeignKey(name = "PROPERTY_TYPE") )
    private Type propertyType;

    @ManyToMany(mappedBy = "favoriteProperties")
    private List<User> users = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_PROPERTY_CITY"))
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name = "FK_PROPERTY_OWNER"))
    private Owner owner;


    public void addOwner(Owner o){
        o.getOwns().add(this);
    }

    public void removeOwner(Owner o){
        this.owner = null;
        o.getOwns().remove(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Property property = (Property) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
