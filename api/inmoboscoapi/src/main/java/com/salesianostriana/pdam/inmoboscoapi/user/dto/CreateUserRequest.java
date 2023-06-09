package com.salesianostriana.pdam.inmoboscoapi.user.dto;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.*;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.stream.Collectors;

@NoArgsConstructor @AllArgsConstructor
@Data
public class CreateUserRequest {

    protected String firstname,lastname,password,passwordRepeat,username,dni,phoneNumber,avatar,email,birthdate,rol;

    public static User createUserFromCreateUserRequest(CreateUserRequest createUserRequest){
        return User.builder()
                .firstname(createUserRequest.firstname)
                .lastname(createUserRequest.lastname)
                .password(createUserRequest.password)
                .username(createUserRequest.username)
                .dni(createUserRequest.getDni())
                .phoneNumber(createUserRequest.phoneNumber)
                .birthdate(LocalDate.parse(createUserRequest.getBirthdate()))
                .email(createUserRequest.getEmail())
                .build();
    }

}
