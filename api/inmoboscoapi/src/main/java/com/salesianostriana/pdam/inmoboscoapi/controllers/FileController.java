package com.salesianostriana.pdam.inmoboscoapi.controllers;


import com.salesianostriana.pdam.inmoboscoapi.others.Storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;


}
