package com.salesianostriana.pdam.inmoboscoapi.user.dto;

import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.PasswordsMatch;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniquePhoneNumber;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniqueUsername;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@PasswordsMatch.List({@PasswordsMatch(passwordField = "password",
        verifyPasswordField = "passwordRepeat", message = "{CreateUserRequest.password.notmatch}")})
public class CreateUserRequest {

    @NotNull
    @NotEmpty(message = "{CreateUserRequest.firtsname.notempty}")
    private String firstname;

    @NotEmpty(message = "{CreateUserRequest.lastname.notempty}")
    private String lastname;

    @NotEmpty(message = "{CreateUserRequest.password.notempty}")
    private String password;

    @NotEmpty(message = "{CreateUserRequest.passwordRepeat.notempty}")
    private String passwordRepeat;

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
