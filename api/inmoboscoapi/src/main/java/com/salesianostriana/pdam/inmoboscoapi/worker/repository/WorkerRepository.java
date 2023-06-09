package com.salesianostriana.pdam.inmoboscoapi.worker.repository;

import com.salesianostriana.pdam.inmoboscoapi.worker.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {
}
