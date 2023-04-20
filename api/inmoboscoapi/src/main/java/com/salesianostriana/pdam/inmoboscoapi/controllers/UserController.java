package com.salesianostriana.pdam.inmoboscoapi.controllers;

import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.dto.JwtUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.dto.LoginRequest;
import com.salesianostriana.pdam.inmoboscoapi.models.User;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.access.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.salesianostriana.pdam.inmoboscoapi.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    /*
        @Operation(summary = "creación de un nuevo usuario")
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = CrearUsuarioRequest.class),
                        examples = {@ExampleObject(
                                value = """
                                            {
                                                "nombre":"David",
                                                "apellidos":"García María",
                                                "password":"12345678",
                                                "passwordRepeat":"12345678",
                                                "userName": "Martinex",
                                                "dni":"85995544X",
                                                "edad":21,
                                                "avatar":"https://robohash.org/81.36.52.170.png",\s
                                                "telefono":"648627905",
                                                "email": "vivaelsevilla@gmail.com"
                                            }
                                        """
                        )})}
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201",
                        description = "Se ha creado correctamente el nuevo usuario con rol de usuario",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = CrearUsuarioResponse.class),
                                examples = {@ExampleObject(
                                        value = """
                                                {
                                                    "id": "ac1d8001-8680-169d-8186-8017ac5f0000",
                                                    "userName": "Martinex",
                                                    "avatar": "https://robohash.org/81.36.52.170.png",
                                                    "nombre": "David",
                                                    "apellido": "García María",
                                                    "role": "USER",
                                                    "fechaCreacion": "2023-02-23"
                                                }
                                                                                            """
                                )})}),
                @ApiResponse(responseCode = "400",
                        description = "Ha habido algún error al intentar crear al nuevo usuario",
                        content = @Content)
        })
        */

    @PostMapping("/auth/register")
    public ResponseEntity<CreateUserResponse> createUserwithUserRole(@RequestBody CreateUserRequest createUserRequest) {

        User u = userService.createUserWithUserRole(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                CreateUserResponse.createUserResponseFromUser(u));
    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<CreateUserResponse> createUserwithWorkerRole(@RequestBody CreateUserRequest createUserRequest) {

        User u = userService.createUserWithWorkerRole(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
    }

    @PostMapping("/auth/register/owner")
    public ResponseEntity<CreateUserResponse> createUserwithOwnerRole(@RequestBody CreateUserRequest createUserRequest) {

        User u = userService.createUserWithOwnerRole(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user,token));

    }



}
