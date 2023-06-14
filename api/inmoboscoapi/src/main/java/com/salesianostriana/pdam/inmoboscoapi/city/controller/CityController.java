package com.salesianostriana.pdam.inmoboscoapi.city.controller;

import com.salesianostriana.pdam.inmoboscoapi.city.dto.CityResponse;
import com.salesianostriana.pdam.inmoboscoapi.city.model.City;
import com.salesianostriana.pdam.inmoboscoapi.city.service.CityService;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {


    private final CityService cityService;

    @Operation(summary = "Obtiene todas las ciudades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la lista de todas las ciudades",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "name": "A Coruna",
                                                    "id": 1
                                                },
                                                {
                                                    "name": "Alava",
                                                    "id": 2
                                                },
                                                {
                                                    "name": "Albacete",
                                                    "id": 3
                                                },
                                                {
                                                    "name": "Alicante",
                                                    "id": 4
                                                }
                                            ]
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
    public ResponseEntity<List<CityResponse>>getAllCities(){
        return ResponseEntity.ok(cityService.findAll().stream().map(CityResponse::of).collect(Collectors.toList()));
    }




}
