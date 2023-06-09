package com.salesianostriana.pdam.inmoboscoapi.city.controller;

import com.salesianostriana.pdam.inmoboscoapi.city.dto.CityResponse;
import com.salesianostriana.pdam.inmoboscoapi.city.model.City;
import com.salesianostriana.pdam.inmoboscoapi.city.service.CityService;
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


    @GetMapping("/")
    public ResponseEntity<List<CityResponse>>getAll(){
        return ResponseEntity.ok(cityService.findAll().stream().map(CityResponse::of).collect(Collectors.toList()));
    }




}
