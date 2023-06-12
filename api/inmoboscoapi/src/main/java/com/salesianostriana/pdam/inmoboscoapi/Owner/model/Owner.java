package com.salesianostriana.pdam.inmoboscoapi.Owner.model;

import com.salesianostriana.pdam.inmoboscoapi.creditCard.model.CreditCard;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Owner extends User {

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> owns = new ArrayList<>();

    @OneToOne(mappedBy = "creditCardOwner",cascade = CascadeType.ALL)
    private CreditCard creditCard;



}

