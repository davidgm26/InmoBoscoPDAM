package com.salesianostriana.pdam.inmoboscoapi.user.dto;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniquePhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.EnumSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class CreateUserResponse {

    protected String firstname,lastname,username,avatar,email,rol;

    @NotEmpty(message = "{CreateUserRequest.birthdate.notempty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthdate;

    @NotEmpty(message = "{CreateUserRequest.phoneNumber.notempty}")
    @UniquePhoneNumber(message = "{CreateUserRequest.phoneNumber.unique}" )
    @Size(min = 9,max = 9, message = "{CreateUserRequest.phoneNumber.phoneNumberLenght}")
    private String phoneNumber;

    protected UUID id;

    protected boolean enabled;

    public static CreateUserResponse createUserResponseFromUser(User u){
        return CreateUserResponse.builder()
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .username(u.getUsername())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .rol(convertRoleToString(u.getRol()))
                .id(u.getId())
                .enabled(u.isEnabled())
                .birthdate(u.getBirthdate().toString())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }

    public static String convertRoleToString(EnumSet<UserRole> roles){
        return roles.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(","));
    }



}
