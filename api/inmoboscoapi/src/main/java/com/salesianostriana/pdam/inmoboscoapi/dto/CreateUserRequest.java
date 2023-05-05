package com.salesianostriana.pdam.inmoboscoapi.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Data
public class CreateUserRequest {

    private String firstname;

    private String lastname;

    private String password;

    private String passwordRepeat;

    private String username;

    private String dni;

    private String phoneNumber;

    private int age;

    private String avatar;

    private String birthdate;

    private String email;

}
