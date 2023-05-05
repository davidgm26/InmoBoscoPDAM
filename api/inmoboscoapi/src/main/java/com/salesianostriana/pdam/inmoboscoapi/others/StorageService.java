package com.salesianostriana.pdam.inmoboscoapi.others;


import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    String store(byte[] file, String filename, String contentType) throws Exception;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteFile(String filename);

    void deleteAll();

}
