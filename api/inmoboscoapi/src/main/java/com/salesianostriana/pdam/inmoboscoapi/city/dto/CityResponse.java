package com.salesianostriana.pdam.inmoboscoapi.city.dto;

import com.salesianostriana.pdam.inmoboscoapi.city.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CityResponse {

    protected String name;
    protected Long id;

    public static CityResponse of(City city){
        return CityResponse.builder()
                .name(city.getName())
                .id(city.getId())
                .build();
    }
}
