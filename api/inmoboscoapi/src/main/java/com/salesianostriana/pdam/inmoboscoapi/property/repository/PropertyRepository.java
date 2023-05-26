package com.salesianostriana.pdam.inmoboscoapi.property.repository;

import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertyRepository extends JpaRepository<Property,Long>, JpaSpecificationExecutor<Property> {
}
