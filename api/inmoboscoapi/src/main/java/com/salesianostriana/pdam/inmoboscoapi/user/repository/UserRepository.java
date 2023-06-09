package com.salesianostriana.pdam.inmoboscoapi.user.repository;

import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {


    Optional<User> findFirstByUsername(String username);


}
