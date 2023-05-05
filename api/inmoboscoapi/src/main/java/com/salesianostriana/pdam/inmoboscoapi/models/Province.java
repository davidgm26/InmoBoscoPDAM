package com.salesianostriana.pdam.inmoboscoapi.models;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    @Builder.Default
    private List<City> cities = new ArrayList<>();



}
