package com.salesianostriana.pdam.inmoboscoapi.exception;

public class PhoneNumberInUseException extends RuntimeException {

    public PhoneNumberInUseException() {
        super(" The phone number entered already exists");
    }

}
