package com.salesianostriana.pdam.inmoboscoapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDate birthdate;

    private String email;

}
