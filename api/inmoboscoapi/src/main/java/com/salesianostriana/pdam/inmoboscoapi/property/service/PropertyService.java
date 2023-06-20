package com.salesianostriana.pdam.inmoboscoapi.property.service;

import com.salesianostriana.pdam.inmoboscoapi.Owner.repository.OwnerRepository;
import com.salesianostriana.pdam.inmoboscoapi.city.repository.CityRepository;
import com.salesianostriana.pdam.inmoboscoapi.exception.EmptyPropertyListException;
import com.salesianostriana.pdam.inmoboscoapi.exception.EmptyUserListException;
import com.salesianostriana.pdam.inmoboscoapi.exception.EmptyUserPropertyList;
import com.salesianostriana.pdam.inmoboscoapi.exception.PropertyNotFoundException;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.CreatePropertyRequest;
import com.salesianostriana.pdam.inmoboscoapi.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.property.TypeRepository;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.property.repository.PropertyRepository;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
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

    public Property findById(Long id){
        return propertyRepository.findById(id).orElseThrow(()-> new PropertyNotFoundException(id));
    }

    public Property createProperty(CreatePropertyRequest propertyRequest){
            Property p = Property.builder()
                    .lat(propertyRequest.getLat())
                    .m2(propertyRequest.getM2())
                    .lon(propertyRequest.getLon())
                    .propertyType(typeRepository.findFirstByTypeContainsIgnoreCase(propertyRequest.getPropertyType()))
                    .name(propertyRequest.getName())
                    .price(propertyRequest.getPrice())
                    .title(propertyRequest.getTitle())
                    .city(cityRepository.findFirstByNameContainsIgnoreCase(propertyRequest.getCity()))
                    .totalBaths(propertyRequest.getTotalBaths())
                    .totalBedRooms(propertyRequest.getTotalBedRooms())
                    .description(propertyRequest.getDescription())
                    .build();
            return propertyRepository.save(p);
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
        }).orElseThrow(()-> new PropertyNotFoundException(id));

    }

    public void deleteProperty(Long id){
        Property p = findById(id);
        p.setOwner(null);
        propertyRepository.delete(p);
    }

    public Page<PropertyResponse> findPropertiesByUser(String username,Pageable pageable){
        Page<PropertyResponse> result = propertyRepository.findAllUserProperties(username,pageable);
        if (result.isEmpty())
            throw new EmptyUserPropertyList(username);
    return result;

    }

    public Page<PropertyResponse> findPropertiesWithFilters(Optional<String> cityName,Optional<String> type, Pageable pageable){
        if(cityName.isEmpty()&&type.isEmpty()){
            Page<Property> aux = propertyRepository.findAll(pageable);
            return aux.map(PropertyResponse::convertPropertyResponseFromProperty);
        }
        if(cityName.isEmpty()){
            Page<Property>aux = propertyRepository.findAllOneSpecificType(type.get(),pageable);
            return aux.map(PropertyResponse::convertPropertyResponseFromProperty);
        }else if(type.isEmpty()){

            Page<Property> aux = propertyRepository.findAllByCity(cityName.get(),pageable);
            return aux.map(PropertyResponse::convertPropertyResponseFromProperty);
        }else{
            Page<Property>aux = propertyRepository.findAllPropertiesWithFilters(type.get(),cityName.get(),pageable);
            return aux.map(PropertyResponse::convertPropertyResponseFromProperty);
        }
    }
}

