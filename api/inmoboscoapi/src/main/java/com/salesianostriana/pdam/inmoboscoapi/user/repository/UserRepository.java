package com.salesianostriana.pdam.inmoboscoapi.user.repository;

import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {



    Optional<User> findFirstByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String username);
    @Query("""
            SELECT CASE WHEN (COUNT(u) > 0) THEN true ELSE false END
            FROM User u
            JOIN u.favoriteProperties f
            WHERE u.id = ?1
            AND f.id = ?2
            """)
    boolean existFavourite(UUID id, Long propertyId);

    @Query("""
            SELECT f
            FROM User u 
            JOIN u.favoriteProperties f
            WHERE u.id = ?1
            """)
    Page<Property> findFavourites(UUID id, Pageable pageable);



}
