package com.salesianostriana.pdam.inmoboscoapi.Owner.repository;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
}
