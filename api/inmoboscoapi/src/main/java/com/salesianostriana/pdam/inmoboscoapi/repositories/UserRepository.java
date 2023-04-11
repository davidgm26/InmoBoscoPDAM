package com.salesianostriana.pdam.inmoboscoapi.repositories;

import com.salesianostriana.pdam.inmoboscoapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    Optional<User> findByUsername(String username);
}