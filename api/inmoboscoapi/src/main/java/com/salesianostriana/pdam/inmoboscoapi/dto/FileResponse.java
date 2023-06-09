package com.salesianostriana.pdam.inmoboscoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FileResponse {

    private String name;
    private String uri;
    private String type;
    private long size;


}
