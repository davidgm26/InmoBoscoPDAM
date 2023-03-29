package com.salesianostriana.pdam.inmoboscoapi.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@DiscriminatorValue("O")
@SuperBuilder
public class Owner extends User{

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "owner_id",
            foreignKey = @ForeignKey(name = "FK_LIKES_USER")),
            inverseJoinColumns = @JoinColumn(name = "property_id",
                    foreignKey = @ForeignKey(name = "FK_LIKES_PROPERTY")),
            name = "likes"
    )
    @Builder.Default
    private List<Property> favourites = new ArrayList<>();

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Property> owns = new ArrayList<>();

    @OneToOne(mappedBy = "creditCardOwner",cascade = CascadeType.ALL)
    private CreditCard creditCard;






}

