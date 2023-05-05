package com.salesianostriana.pdam.inmoboscoapi.dto;

import com.salesianostriana.pdam.inmoboscoapi.models.User;
import com.salesianostriana.pdam.inmoboscoapi.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.EnumSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class CreateUserResponse {

    protected String firstname;

    protected String lastname;

    protected String username;
    protected String avatar;

    protected String email;

    protected String rol;

    protected UUID id;

    public static CreateUserResponse createUserResponseFromUser(User u){
        return CreateUserResponse.builder()
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .username(u.getUsername())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .rol(convertRoleToString(u.getRol()))
                .id(u.getId())
                .build();
    }

    public static String convertRoleToString(EnumSet<UserRole> roles){
        return roles.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(","));
    }


}
