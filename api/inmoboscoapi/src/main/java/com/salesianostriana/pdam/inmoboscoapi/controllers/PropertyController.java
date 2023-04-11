package com.salesianostriana.pdam.inmoboscoapi.controllers;

import com.salesianostriana.pdam.inmoboscoapi.services.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class PropertyController {

    private final PropertyService propertyService;



}
