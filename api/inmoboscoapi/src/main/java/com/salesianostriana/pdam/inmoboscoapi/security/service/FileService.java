package com.salesianostriana.pdam.inmoboscoapi.security.service;

import com.salesianostriana.pdam.inmoboscoapi.dto.FileResponse;
import com.salesianostriana.pdam.inmoboscoapi.others.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@AllArgsConstructor
public class FileService {

    private final StorageService storageService;

    public FileResponse uploadFile(MultipartFile file){

        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/")
                .path(name)
                .toUriString();

        return FileResponse.builder()
                .name(name)
                .uri(uri)
                .type(file.getContentType())
                .build();
    }



}
