package com.salesianostriana.pdam.inmoboscoapi.city.repository;

import com.salesianostriana.pdam.inmoboscoapi.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {

    City findFirstByNameContainsIgnoreCase(String tipoInmueble);

}
