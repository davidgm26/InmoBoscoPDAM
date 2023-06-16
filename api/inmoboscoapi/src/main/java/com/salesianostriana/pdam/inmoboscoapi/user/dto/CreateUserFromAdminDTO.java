package com.salesianostriana.pdam.inmoboscoapi.user.dto;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.PasswordsMatch;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniquePhoneNumber;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.EnumSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserFromAdminDTO {

    @NotNull
    @NotEmpty(message = "{CreateUserRequest.firtsname.notempty}")
    private String firstname;

    @NotEmpty(message = "{CreateUserRequest.lastname.notempty}")
    private String lastname;

    @NotEmpty(message = "{CreateUserRequest.password.notempty}")
    private String password;

    @NotEmpty(message = "{CreateUserRequest.username.notempty}")
    @UniqueUsername(message ="{CreateUserRequest.username.unique}")
    private String username;

    @NotEmpty(message = "{CreateUserRequest.dni.notempty}")
    @Size(min = 9)
    private String dni;

    @NotEmpty(message = "{CreateUserRequest.phoneNumber.notempty}")
    @UniquePhoneNumber(message = "{CreateUserRequest.phoneNumber.unique}" )
    @Size(min = 9,max = 9, message = "{CreateUserRequest.phoneNumber.phoneNumberLenght}")
    private String phoneNumber;

    private String avatar;

    @NotEmpty(message = "{CreateUserRequest.email.notempty}")
    @Email(message = "{CreateUserRequest.email.format}")
    private String email;

    @NotEmpty(message = "{CreateUserRequest.birthdate.notempty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthdate;

    private String rol;

    public static User createUserFromAdmin(CreateUserFromAdminDTO createFromAdminUserRequest){
        return User.builder()
                .firstname(createFromAdminUserRequest.firstname)
                .lastname(createFromAdminUserRequest.lastname)
                .password(createFromAdminUserRequest.password)
                .username(createFromAdminUserRequest.username)
                .dni(createFromAdminUserRequest.getDni())
                .phoneNumber(createFromAdminUserRequest.phoneNumber)
                .birthdate(LocalDate.parse(createFromAdminUserRequest.getBirthdate()))
                .email(createFromAdminUserRequest.getEmail())
                .rol(EnumSet.of(UserRole.valueOf(createFromAdminUserRequest.getRol().toUpperCase())))
                .build();
    }

}
