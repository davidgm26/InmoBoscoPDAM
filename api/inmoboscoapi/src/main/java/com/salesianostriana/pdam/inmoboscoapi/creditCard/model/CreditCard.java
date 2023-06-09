package com.salesianostriana.pdam.inmoboscoapi.creditCard.model;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String CVV;

    private Date validDate;


    @OneToOne
    @JoinColumn(name = "FK_OWNER", updatable = false, nullable = false)
    private Owner creditCardOwner;

}
