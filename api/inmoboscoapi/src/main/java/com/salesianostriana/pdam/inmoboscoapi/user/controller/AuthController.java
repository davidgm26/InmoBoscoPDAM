package com.salesianostriana.pdam.inmoboscoapi.user.controller;

import com.salesianostriana.pdam.inmoboscoapi.security.dto.JwtUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.security.dto.LoginRequest;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.access.JwtProvider;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshToken;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.pdam.inmoboscoapi.security.service.RefreshTokenService;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;



    @PostMapping("/register/worker")
    public ResponseEntity<CreateUserResponse> createUserwithWorkerRole(@RequestBody CreateUserRequest createUserRequest) {

        User u = userService.createUserWithWorkerRole(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
    }


    /*Sacar el usuario del contexto de seguridaa*/
    @PutMapping("/register/owner/{id}")
    public ResponseEntity<CreateUserResponse> createUserwithOwnerRole() {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(u.getUsername());
        userService.addOwnerRole(u.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
    }

    /*
        @Operation(summary = "creación de un nuevo usuario")
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = CrearUsuarioRequest.class),
                        examples = {@ExampleObject(
                                value = """
                                            {code
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
                                                    "firstname": "David",
                                                    "lastname": "García María",
                                                    "username": "Martinex",
                                                    "avatar": "https://robohash.org/81.36.52.170.png",
                                                    "email": "vivaelsevilla@gmail.com",
                                                    "rol": "USER",
                                                    "id": "ac169001-8857-1cde-8188-57fd36c50000"
                                                }
                                                                                            """
                                )})}),
                @ApiResponse(responseCode = "400",
                        description = "Ha habido algún error al intentar crear al nuevo usuario",
                        content = @Content)
        })
        */
    @PostMapping("/register/user")
    public ResponseEntity<CreateUserResponse> createUserwithUserRole(@RequestBody CreateUserRequest createUserRequest) {

        User u = userService.createUserWithUserRole(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
    }

    @PostMapping("/login")
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

        refreshTokenService.deleteByUser(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        System.out.println(user.getRol());

        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user, token, refreshToken.getToken()));

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateToken(user);
                    refreshTokenService.deleteByUser(user);
                    RefreshToken refreshToken2 = refreshTokenService.createRefreshToken(user);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(JwtUserResponse.builder()
                                    .token(token)
                                    .refreshToken(refreshToken2.getToken())
                                    .build());
                })
                .orElseThrow(() -> new RefreshTokenException("Refresh token not found"));

    }



}
