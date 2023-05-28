package com.salesianostriana.pdam.inmoboscoapi.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUserResponse extends CreateUserResponse {

    private String token;
    private String refreshToken;

    public JwtUserResponse (CreateUserResponse createUserResponse){
        id =createUserResponse.getId();
        username = createUserResponse.getUsername();
        firstname = createUserResponse.getFirstname();
        lastname = createUserResponse.getLastname();
        avatar = createUserResponse.getAvatar();
        email = createUserResponse.getEmail();
        rol = createUserResponse.getRol();

    }

    public static JwtUserResponse of (User user , String token,String refreshToken){
        JwtUserResponse result = new JwtUserResponse(CreateUserResponse.createUserResponseFromUser(user));
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        return result;
    }

}
