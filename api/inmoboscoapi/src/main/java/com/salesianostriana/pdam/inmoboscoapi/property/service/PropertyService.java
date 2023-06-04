package com.salesianostriana.pdam.inmoboscoapi.property.service;

import com.salesianostriana.pdam.inmoboscoapi.city.service.CityRepository;
import com.salesianostriana.pdam.inmoboscoapi.exception.EmptyPropertyListException;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.CreatePropertyRequest;
import com.salesianostriana.pdam.inmoboscoapi.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.property.TypeRepository;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final TypeRepository typeRepository;

    private final CityRepository cityRepository;


    public Page<PropertyResponse> findAll(List<SearchCriteria> params, Pageable pageable){

        GenericSpecificationBuilder<Property> propertyGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params);

        Specification<Property> spec = propertyGenericSpecificationBuilder.build();
        Page<PropertyResponse> result = propertyRepository.findAll(spec,pageable).map(PropertyResponse::convertPropertyResponseFromProperty);

        if(result.isEmpty())
         throw new EmptyPropertyListException();

        return result;

    }

    public Optional<Property> findById(Long id){
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property){
        return propertyRepository.save(property);
    }

    public Property editProperty(CreatePropertyRequest property, Long id){
        return propertyRepository.findById(id).map(prop -> {
            prop.setLat(property.getLat());
            prop.setLon(property.getLon());
            prop.setName(property.getName());
            prop.setTitle(property.getTitle());
            prop.setPrice(property.getPrice());
            prop.setM2(property.getM2());
            prop.setPropertyType(typeRepository.findFirstByTypeContainsIgnoreCase(property.getPropertyType()));
            prop.setCity(cityRepository.findFirstByNameContainsIgnoreCase(property.getCity()));prop.setDescription(property.getDescription());
            prop.setTotalBaths(property.getTotalBaths());
            prop.setTotalBedRooms(property.getTotalBedRooms());
            prop.setDescription(property.getDescription());
            return propertyRepository.save(prop);
        }).orElseThrow();
    }

    public void deleteProperty(Long id){
        Optional<Property> P = findById(id);
        propertyRepository.deleteById(id);
    }



}
