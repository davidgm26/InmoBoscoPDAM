package com.salesianostriana.pdam.inmoboscoapi.services;

import com.salesianostriana.pdam.inmoboscoapi.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;



}
