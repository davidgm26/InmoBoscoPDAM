package com.salesianostriana.pdam.inmoboscoapi.dto;

import com.salesianostriana.pdam.inmoboscoapi.models.User;
import com.salesianostriana.pdam.inmoboscoapi.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.EnumSet;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class CreateUserResponse {

    private String firstname;

    private String lastname;

    private String password;

    private String username;

    private String avatar;

    private String email;

    private String rol;

    public static CreateUserResponse createUserResponseFromUser(User u){
        return CreateUserResponse.builder()
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .password(u.getPassword())
                .username(u.getUsername())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .rol(convertRoleToString(u.getRol()))
                .build();
    }

    public static String convertRoleToString(EnumSet<UserRole> roles){
        return roles.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(","));
    }


}
