package com.salesianostriana.pdam.inmoboscoapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EditUserPassword {

    protected String newPassword;

    protected String repeatNewPassword;



}
