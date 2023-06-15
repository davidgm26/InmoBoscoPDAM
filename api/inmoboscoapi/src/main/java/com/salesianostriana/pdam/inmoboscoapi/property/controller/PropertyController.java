package com.salesianostriana.pdam.inmoboscoapi.property.controller;

import com.salesianostriana.pdam.inmoboscoapi.property.dto.CreatePropertyRequest;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.property.service.PropertyService;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteriaExtractor;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property")
@Tag(name = "Property", description = "Controlador para gestionar todas las funciones con las propiedades")
public class PropertyController {

    private final PropertyService propertyService;


    @Operation(summary = "Obtiene todas las propiedades paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la lista de propiedades paginadas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropertyResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": 1,
                                                        "lat": "40.4167754",
                                                        "lon": "-3.7037902",
                                                        "name": "Apartamento en el centro",
                                                        "title": "Hermoso apartamento cerca de todos los servicios",
                                                        "price": 1500.89,
                                                        "m2": 80.0,
                                                        "description": "Este apartamento luminoso y espacioso se encuentra en el corazÃ³n de la ciudad.",
                                                        "totalBedRooms": 2,
                                                        "totalBaths": 1,
                                                        "propertyType": "Apartamento",
                                                        "city": "A Coruna",
                                                        "owner": "Martinex"
                                                    },
                                                    {
                                                        "id": 2,
                                                        "lat": "41.3850639",
                                                        "lon": "2.1734035",
                                                        "name": "Casa de lujo en la playa",
                                                        "title": "Espectacular casa con vistas al mar",
                                                        "price": 2500.0,
                                                        "m2": 150.0,
                                                        "description": "Esta impresionante casa ofrece vistas panorÃ¡micas al mar y todas las comodidades necesarias.",
                                                        "totalBedRooms": 4,
                                                        "totalBaths": 3,
                                                        "propertyType": "Casa",
                                                        "city": "Granada",
                                                        "owner": "Martinex"
                                                    },
                                                    {
                                                        "id": 3,
                                                        "lat": "39.4699019",
                                                        "lon": "-0.3773957",
                                                        "name": "Piso moderno en el barrio histÃ³rico",
                                                        "title": "Acogedor piso cerca de lugares histÃ³ricos",
                                                        "price": 1200.0,
                                                        "m2": 100.0,
                                                        "description": "Este piso moderno y elegante se encuentra en el encantador barrio histÃ³rico de la ciudad.",
                                                        "totalBedRooms": 3,
                                                        "totalBaths": 2,
                                                        "propertyType": "Piso",
                                                        "city": "Albacete",
                                                        "owner": "Martinex"
                                                    },
                                                    {
                                                        "id": 4,
                                                        "lat": "37.3890924",
                                                        "lon": "-5.9844589",
                                                        "name": "Casa con jardÃ­n amplio",
                                                        "title": "Amplia casa, ideal para familias",
                                                        "price": 3000.0,
                                                        "m2": 200.0,
                                                        "description": "Esta espaciosa casa cuenta con un amplio jardÃ­n y es perfecta para familias.",
                                                        "totalBedRooms": 4,
                                                        "totalBaths": 3,
                                                        "propertyType": "Casa",
                                                        "city": "Alicante",
                                                        "owner": ""
                                                    },
                                                    {
                                                        "id": 5,
                                                        "lat": "43.2630126",
                                                        "lon": "-2.9349852",
                                                        "name": "Apartamento en el casco antiguo",
                                                        "title": "Encantador apartamento en el corazÃ³n de la ciudad",
                                                        "price": 1800.0,
                                                        "m2": 90.0,
                                                        "description": "Este encantador apartamento se encuentra en el pintoresco casco antiguo, cerca de todos los lugares de interÃ©s.",
                                                        "totalBedRooms": 2,
                                                        "totalBaths": 1,
                                                        "propertyType": "Apartamento",
                                                        "city": "Almeria",
                                                        "owner": ""
                                                    }
                                                ],
                                                "totalElements": 51,
                                                "totalPages": 11,
                                                "size": 5,
                                                "number": 0,
                                            }
                                                                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "Full authentication is required to access this resource",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT",
                    content = @Content),
    })


    @GetMapping("/")
    public Page<PropertyResponse> getAllProperties(@RequestParam(value = "search", defaultValue = "") String search,
                                                   @PageableDefault(size = 5, page = 0) Pageable pageable) {
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        return propertyService.findAll(params, pageable);
    }

    @Operation(summary = "Obtiene todos los detalles de una propiedad buscada por id")
    @Parameter(description = "Id de la propiedad de la cual deseas los detalles", name = "id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente todos los detalles de la propiedad",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropertyResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "lat": "40.4167754",
                                                "lon": "-3.7037902",
                                                "name": "Apartamento en el centro",
                                                "title": "Hermoso apartamento cerca de todos los servicios",
                                                "price": 1500.89,
                                                "m2": 80.0,
                                                "description": "Este apartamento luminoso y espacioso se encuentra en el corazÃ³n de la ciudad.",
                                                "totalBedRooms": 2,
                                                "totalBaths": 1,
                                                "propertyType": "Apartamento",
                                                "city": "A Coruna",
                                                "owner": "Martinex"
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "Full authentication is required to access this resource",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getOneProperty(@PathVariable Long id) {
        return ResponseEntity.ok(PropertyResponse.convertPropertyResponseFromProperty(propertyService.findById(id)));
    }

    @Operation(summary = "creación de un nueva propiedad")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreatePropertyRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "lat": "40.4167754",
                                            "lon": "-3.7037902",
                                            "name": "Apartamento en el centro",
                                            "title": "Hermoso apartamento cerca de todos los servicios",
                                            "price": 1500.89,
                                            "m2": 80.0,
                                            "description": "Este apartamento luminoso y espacioso se encuentra en el corazon de la ciudad.",
                                            "totalBedRooms": 2,
                                            "totalBaths": 1,
                                            "propertyType": "Piso",
                                            "city": "sevilla"
                                        }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente el nuevo usuario con rol de User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropertyResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                   "id": 0,
                                                   "lat": "40.4167754",
                                                   "lon": "-3.7037902",
                                                   "name": "Apartamento en el centro",
                                                   "title": "Hermoso apartamento cerca de todos los servicios",
                                                   "price": 1500.89,
                                                   "m2": 80.0,
                                                   "description": "Este apartamento luminoso y espacioso se encuentra en el corazon de la ciudad.",
                                                   "totalBedRooms": 2,
                                                   "totalBaths": 1,
                                                   "propertyType": "Piso",
                                                   "city": "Sevilla",
                                                   "owner": ""
                                               }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar crear al nuevo usuario con rol de User",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<PropertyResponse> createProperty(@RequestBody CreatePropertyRequest createPropertyRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(PropertyResponse.convertPropertyResponseFromProperty(propertyService.createProperty(createPropertyRequest)));
    }

    @Operation(summary = "Edición de una propiedad buscada por id")
    @Parameter(description = "Id de la propiedad que se desea editar", name = "id", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreatePropertyRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "lat": "40.4167754",
                                            "lon": "-3.7037902",
                                            "name": "Apartamento en el centro",
                                            "title": "Hermoso apartamento cerca de todos los servicios",
                                            "price": 0.0,
                                            "m2": 80.0,
                                            "description": "Este apartamento luminoso y espacioso se encuentra en el corazooonn de la ciudad.",
                                            "totalBedRooms": 2,
                                            "totalBaths": 1,
                                            "propertyType": "Apartamento",
                                            "city": "Sevilla"
                                        }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente el nuevo usuario con rol de User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PropertyResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "lat": "40.4167754",
                                                "lon": "-3.7037902",
                                                "name": "Apartamento en el centro",
                                                "title": "Hermoso apartamento cerca de todos los servicios",
                                                "price": 0.0,
                                                "m2": 80.0,
                                                "description": "Este apartamento luminoso y espacioso se encuentra en el corazooonn de la ciudad.",
                                                "totalBedRooms": 2,
                                                "totalBaths": 1,
                                                "propertyType": "Apartamento",
                                                "city": "Sevilla",
                                                "owner": "Martinex"
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar crear al nuevo usuario con rol de User",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> editProperty(@RequestBody CreatePropertyRequest property, @PathVariable Long id) {
        Property prop = propertyService.editProperty(property, id);
        return ResponseEntity.ok(PropertyResponse.convertPropertyResponseFromProperty(prop));
    }

    @Operation(summary = "Endpoint para borrar una propiedad de la base de datos")
    @Parameter(description = "El id de la propiedad que se desea borrar", name = "id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente la propiedad",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }


}
