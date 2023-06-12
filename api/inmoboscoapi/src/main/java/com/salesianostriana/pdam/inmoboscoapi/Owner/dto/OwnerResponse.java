package com.salesianostriana.pdam.inmoboscoapi.Owner.dto;

import com.salesianostriana.pdam.inmoboscoapi.Owner.model.Owner;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerResponse {

    private String username;
    private String firstname;
    private String lastname;

    public static OwnerResponse of (Owner o){

        return  OwnerResponse.builder()
                .username(o.getUsername())
                .firstname(o.getFirstname())
                .lastname(o.getLastname())
                .build();

    }







}
