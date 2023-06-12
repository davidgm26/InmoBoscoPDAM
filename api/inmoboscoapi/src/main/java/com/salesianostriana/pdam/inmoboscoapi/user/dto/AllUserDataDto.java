package com.salesianostriana.pdam.inmoboscoapi.user.dto;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AllUserDataDto{

    private  UUID id;
    private String rol,firstname,lastname,username,password,dni,avatar,phoneNumber,email;
    private LocalDate birthdate;

    private LocalDateTime createdAt;

    private boolean accountNonLocked,enabled;

    public static AllUserDataDto fromUser(User u){
        return AllUserDataDto.builder()
                .id(u.getId())
                .rol(convertRoleToString(u.getRol()))
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .username(u.getUsername())
                .password(u.getPassword())
                .dni(u.getDni())
                .avatar(u.getAvatar())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .birthdate(u.getBirthdate())
                .createdAt(u.getCreatedAt())
                .accountNonLocked(u.isAccountNonLocked())
                .enabled(u.isEnabled())
                .build();
    }
    public static String convertRoleToString(EnumSet<UserRole> roles){
        return roles.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(","));
    }
}
