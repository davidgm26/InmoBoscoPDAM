package com.salesianostriana.pdam.inmoboscoapi.city.model;

import com.salesianostriana.pdam.inmoboscoapi.comments.model.Province;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Property> dwellings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;


}
