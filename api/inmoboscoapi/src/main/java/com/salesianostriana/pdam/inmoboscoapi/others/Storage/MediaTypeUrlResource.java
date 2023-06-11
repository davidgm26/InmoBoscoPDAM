package com.salesianostriana.pdam.inmoboscoapi.others.Storage;

import com.salesianostriana.pdam.inmoboscoapi.exception.StorageException;
import org.springframework.core.io.UrlResource;
import org.apache.tika.Tika;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MediaTypeUrlResource extends UrlResource {

    public MediaTypeUrlResource(URL url) {
        super(url);
    }

    public MediaTypeUrlResource(URI uri) throws MalformedURLException {
        super(uri);
    }

    public MediaTypeUrlResource(String path) throws MalformedURLException {
        super(path);
    }

    public MediaTypeUrlResource(String protocol, String location) throws MalformedURLException {
        super(protocol, location);
    }

    public MediaTypeUrlResource(String protocol, String location, String fragment) throws MalformedURLException {
        super(protocol, location, fragment);
    }

    public String getType() {
        Tika t = new Tika();
        try {
            return t.detect(this.getFile());
        } catch (IOException ex) {
            throw new StorageException("Error trying to get the MIME type", ex);
        }
    }
}
