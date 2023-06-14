package com.salesianostriana.pdam.inmoboscoapi.property.repository;

import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PropertyRepository extends JpaRepository<Property,Long>, JpaSpecificationExecutor<Property> {

    @Query("""
        SELECT new com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse(p.id, p.lat, p.lon, p.name, p.title, p.price, p.m2,
        p.description, p.totalBedRooms, p.totalBaths, p.propertyType.type, p.city.name,p.owner.username,p.img)
        FROM Property p
        WHERE p.owner.username = ?1
        """)
    Page<PropertyResponse> findAllUserProperties(String username, Pageable pageable);


}

