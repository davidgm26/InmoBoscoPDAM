package com.salesianostriana.pdam.inmoboscoapi.repositories;

import com.salesianostriana.pdam.inmoboscoapi.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {
}
