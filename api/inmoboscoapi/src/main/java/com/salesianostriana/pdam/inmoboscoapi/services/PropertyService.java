package com.salesianostriana.pdam.inmoboscoapi.services;

import com.salesianostriana.pdam.inmoboscoapi.models.Property;
import com.salesianostriana.pdam.inmoboscoapi.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;


    public List<Property> findAll(){
        return propertyRepository.findAll();
    }

    public Optional<Property> findById(Long id){
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property){
        return propertyRepository.save(property);
    }

    public Property editProperty(Property property,Long id){
        Property p = propertyRepository.findById(id).map(prop -> {
            prop.setCity(property.getCity());
            prop.setM2(property.getM2());
            return propertyRepository.save(prop);
        }).orElseThrow();
        return p;
    }

    public void deleteProperty(Long id){
        Optional<Property> P = findById(id);
        propertyRepository.deleteById(id);
    }



}
