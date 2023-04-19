package com.salesianostriana.pdam.inmoboscoapi.dto;

import com.salesianostriana.pdam.inmoboscoapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class JwtUserResponse extends CreateUserResponse{

    private String token;

    public JwtUserResponse (CreateUserResponse createUserResponse){
        id=createUserResponse.getId();
        username = createUserResponse.getUsername();
        firstname = createUserResponse.getFirstname();
        lastname = createUserResponse.getLastname();
        avatar = createUserResponse.getAvatar();
        email = createUserResponse.getEmail();
    }

    public static JwtUserResponse of (User user , String token){
        JwtUserResponse result = new JwtUserResponse(CreateUserResponse.createUserResponseFromUser(user));
        result.setToken(token);
        return result;
    }

}
