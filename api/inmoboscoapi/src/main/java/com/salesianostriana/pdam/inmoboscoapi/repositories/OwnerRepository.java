package com.salesianostriana.pdam.inmoboscoapi.repositories;

import com.salesianostriana.pdam.inmoboscoapi.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
}
