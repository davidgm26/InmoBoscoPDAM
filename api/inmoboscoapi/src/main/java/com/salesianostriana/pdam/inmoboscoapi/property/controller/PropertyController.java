package com.salesianostriana.pdam.inmoboscoapi.property.controller;

import com.salesianostriana.pdam.inmoboscoapi.property.dto.CreatePropertyRequest;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.property.service.PropertyService;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteriaExtractor;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    /*
        @Operation(summary = "Obtiene todos los inmuebles paginados")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se ha obtenido correctamente la lista de viviendas paginadas",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = InmuebleResponse.class),
                                examples = {@ExampleObject(
                                        value = """
                                                {
                                                     "content": [
                                                         {
                                                             "tipo": "Casa",
                                                             "descripcion": "Apartamento reformado con vistas al río. Cocina americana con electrodomÃ©sticos. Suelos de tarima. Totalmente amueblado. Zona muy tranquila.",
                                                             "precio": 125000.0,
                                                             "ubicacion": "Triana",
                                                             "metrosCuadrados": 80.5,
                                                             "numBanios": 2,
                                                             "numHab": 1
                                                         },
                                                         {
                                                             "tipo": "Piso",
                                                             "descripcion": "Piso muy luminoso, 3 habitaciones y 2 baÃ±os. Cocina amueblada. Suelos de mÃ¡rmol. Garaje y trastero incluidos. Zona residencial tranquila.",
                                                             "precio": 240000.0,
                                                             "ubicacion": "Los Remedios",
                                                             "metrosCuadrados": 120.0,
                                                             "numBanios": 3,
                                                             "numHab": 2
                                                         },
                                                         {
                                                             "tipo": "Adosado",
                                                             "descripcion": "Ã�tico de lujo con terraza y vistas a la ciudad. 4 habitaciones y 3 baÃ±os. Cocina completamente equipada. Suelos de madera. Aire acondicionado centralizado. Zona cÃ©ntrica y bien comunicada.",
                                                             "precio": 340000.0,
                                                             "ubicacion": "El Porvenir",
                                                             "metrosCuadrados": 180.0,
                                                             "numBanios": 4,
                                                             "numHab": 3
                                                         },
                                                         {
                                                             "tipo": "Casa",
                                                             "descripcion": "Acogedor apartamento en zona cÃ©ntrica. Totalmente reformado y amueblado. 1 habitaciÃ³n y 1 baÃ±o. Suelos de tarima. Aire acondicionado y calefacciÃ³n.",
                                                             "precio": 180000.0,
                                                             "ubicacion": "San Bernardo",
                                                             "metrosCuadrados": 90.0,
                                                             "numBanios": 1,
                                                             "numHab": 1
                                                         },
                                                         {
                                                             "tipo": "Piso",
                                                             "descripcion": "Piso amplio y luminoso con balcÃ³n. 3 habitaciones y 2 baÃ±os. Cocina amueblada. Suelos de terrazo. Aire acondicionado centralizado. Zona comercial y bien comunicada.",
                                                             "precio": 295000.0,
                                                             "ubicacion": "NerviÃ³n",
                                                             "metrosCuadrados": 140.0,
                                                             "numBanios": 3,
                                                             "numHab": 2
                                                         }
                                                     ],
                                                """
                                )})}),
                @ApiResponse(responseCode = "401",
                        description = "Full authentication is required to access this resource",
                        content = @Content),
                @ApiResponse(responseCode = "403",
                        description = "Se ha expirado el token JWT",
                        content = @Content),
        })

     */
    @GetMapping("/")
    public Page<PropertyResponse> getAllProperties(@RequestParam(value = "search", defaultValue = "") String search,
                                                   @PageableDefault(size = 5, page = 0) Pageable pageable) {
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        return propertyService.findAll(params, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getOneProperty(@PathVariable Long id) {
        return ResponseEntity.ok(PropertyResponse.convertPropertyResponseFromProperty(propertyService.findById(id)));
    }

    @PostMapping("/")
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> editProperty(@RequestBody CreatePropertyRequest property, @PathVariable Long id) {
        Property prop = propertyService.editProperty(property, id);
        return ResponseEntity.ok(PropertyResponse.convertPropertyResponseFromProperty(prop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }



}
