package com.salesianostriana.pdam.inmoboscoapi.user.dto;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniquePhoneNumber;
import com.salesianostriana.pdam.inmoboscoapi.user.validation.annotation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor  @NoArgsConstructor
public class EditUserRequest {


    @NotNull
    @NotEmpty(message = "{CreateUserRequest.firtsname.notempty}")
    private String firstname;

    @NotEmpty(message = "{CreateUserRequest.lastname.notempty}")
    private String lastname;

    @NotEmpty(message = "{CreateUserRequest.username.notempty}")
    @UniqueUsername(message ="{CreateUserRequest.username.unique}")
    private String username;


    private String password;

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

    public static User createUserFromEditUserRequest (EditUserRequest newInfo,User u){
                u.setFirstname(newInfo.getFirstname());
                u.setLastname(newInfo.getLastname());
                u.setUsername(newInfo.getUsername());
                u.setDni(newInfo.getDni());
                u.setBirthdate(LocalDate.parse(newInfo.getBirthdate()));
                u.setPhoneNumber(newInfo.getPhoneNumber());
                u.setEmail(newInfo.getEmail());
        return u;
    }

}
