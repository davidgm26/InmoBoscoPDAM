package com.salesianostriana.pdam.inmoboscoapi.repositories;

import com.salesianostriana.pdam.inmoboscoapi.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertyRepository extends JpaRepository<Property,Long>, JpaSpecificationExecutor<Property> {
}