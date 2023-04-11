package com.salesianostriana.pdam.inmoboscoapi.controllers;

import com.salesianostriana.pdam.inmoboscoapi.models.Property;
import com.salesianostriana.pdam.inmoboscoapi.services.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class PropertyController {

    private final PropertyService propertyService;


    @GetMapping("/")
    public ResponseEntity<List<Property>> getAllProperties(){
        return ResponseEntity.ok(propertyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Property>> getOneProperty(@PathVariable Long id){
        return ResponseEntity.ok(propertyService.findById(id));
    }

    @PostMapping("/")
    public Property createProperty(@RequestBody Property property){
        return propertyService.createProperty(property);
    }

    @PutMapping("/{id}")
    public Property editProperty(@RequestBody Property property,@PathVariable Long id){
        return propertyService.editProperty(property,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id){
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

}
