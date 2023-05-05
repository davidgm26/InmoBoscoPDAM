package com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh;

import com.salesianostriana.pdam.inmoboscoapi.security.errorhandling.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg){
        super(msg);
    }

}
