package com.salesianostriana.pdam.inmoboscoapi.user.dto;
import com.salesianostriana.pdam.inmoboscoapi.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor  @NoArgsConstructor
public class EditUserRequest {

    protected String firstname;

    protected String lastname;

    protected String username;

    protected String password;

    protected String dni;

    protected String avatar;

    protected LocalDate birthdate;

    protected String phoneNumber;

    protected String email;

    public static User createUserFromEditUserRequest (EditUserRequest newInfo,User u){
                u.setFirstname(newInfo.getFirstname());
                u.setLastname(newInfo.getLastname());
                u.setUsername(newInfo.getUsername());
                u.setDni(newInfo.getDni());
                u.setBirthdate(newInfo.getBirthdate());
                u.setPhoneNumber(newInfo.getPhoneNumber());
                u.setEmail(newInfo.getEmail());
        return u;
    }

}
