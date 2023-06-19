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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Admin  ", description = "Controlador para todas las funcionalidades de registro de un usuario")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;

/*

    @Operation(summary = "creación de un nuevo Worker")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateUserRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                             "firstname":"Sara",
                                             "lastname":"Maria Braña",
                                             "password":"admin",
                                             "passwordRepeat":"admin",
                                             "username":"Saramb",
                                             "dni":"52266233R",
                                             "phoneNumber":"682625268",
                                             "avatar":"https://robohash.org/ElBrujo",
                                             "email":"vivaeljaen@gmail.com",
                                             "birthdate":"2005-01-11"\s
                                         }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente el nuevo usuario con rol de User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "firstname": "Sara",
                                                "lastname": "Maria Braña",
                                                "username": "Saramb",
                                                "avatar": "default.jpeg",
                                                "email": "vivaeljaen@gmail.com",
                                                "rol": "WORKER",
                                                "id": "ac169001-88b6-108e-8188-b616c83f0001",
                                                "enabled": true
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar crear al nuevo usuario con rol de User",
                    content = @Content)
    })
    @PostMapping("/register/worker")
    public ResponseEntity<CreateUserResponse> createUserwithWorkerRole(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User u = userService.createUserWithWorkerRole(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
    }*/


    /*Sacar el usuario del contexto de seguridad

    @PutMapping("/register/owner")
    public ResponseEntity<CreateUserResponse> createUserwithOwnerRole(@AuthenticationPrincipal User user) {
        if(user == null){
            throw new NotLoggedUser();
        }else{
            userService.addOwnerRole(user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(user));
        }
    }
*/

    @Operation(summary = "creación de un nuevo User")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateUserRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "firstname":"David",
                                            "lastname":"Beckam Perez",
                                            "password":"ofabuloso",
                                            "passwordRepeat":"ofabuloso",
                                            "username":"FlorentinoGuapo",
                                            "dni":"69696969X",
                                            "phoneNumber":"622578415",
                                            "avatar":"https://robohash.org/81.36.52.120.png",
                                            "email":"vivaelmadrid@gmail.com",
                                            "birthdate":"2020-06-12"\s
                                        }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente el nuevo usuario con rol de User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "firstname": "David",
                                                 "lastname": "Beckam Perez",
                                                 "username": "FlorentinoGuapo",
                                                 "avatar": "default.jpeg",
                                                 "email": "vivaelmadrid@gmail.com",
                                                 "rol": "USER",
                                                 "id": "ac169001-88b6-108e-8188-b60b82620000",
                                                 "enabled": true
                                             }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Ha habido algún error al intentar crear al nuevo usuario con rol de User",
                    content = @Content)
    })
    @PostMapping("/register/user")
    public ResponseEntity<CreateUserResponse> registerUser(
            @Valid @RequestPart("user") CreateUserRequest createUserRequest,
            @RequestPart( value = "file",required=false)MultipartFile file) {

        User u = userService.registerUser(createUserRequest,file);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));

    }

    @Operation(summary = "Inicio de sesion")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                        "username":"Martinex",
                                        "password":"12345678"
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "firstname": "David",
                                                "lastname": "Garcia Maria",
                                                "username": "Martinex",
                                                "avatar": "default.jpg",
                                                "email": "vivaelsevilla@gmail.com",
                                                "rol": "OWNER,USER",
                                                "id": "2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                                "enabled": true,
                                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyYmQ5ZTc2MC1hMTFlLTVkOGYtOTY0MS0xYzU0ZTc5YzU3YTEiLCJpYXQiOjE2ODY2ODE1MjMsImV4cCI6MTY4NjY4NTEyM30.NybRDQ6cBfHUpjggilklFaO0SGzBPUXBqwdzoFrbEj3N63MXwS4szbb-w-eMb24I3NZF_XoPU23rR8SWlGwWXQ",
                                                "refreshToken": "5663a413-c3b9-4e53-94ad-babe28c69fde"
                                            }
                                             """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "Las credenciales que se han introducido son erróneas",
                    content = @Content)
    })
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

        return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user, token, refreshToken.getToken()));

    }


    @Operation(summary = "Con este endpoint se puede refrescar el token de refresco")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RefreshTokenRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                      "refreshToken": "b6bc70db-726d-4f10-acd5-f8387eb4118f"
                                    }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "enabled": false,
                                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyYmQ5ZTc2MC1hMTFlLTVkOGYtOTY0MS0xYzU0ZTc5YzU3YTEiLCJpYXQiOjE2ODY2ODE3MjQsImV4cCI6MTY4NjY4NTMyNH0.VNDgDDMr8eGQa7snp8hKpU1bjX15SpPb2aTxWhQD7SinXKftudmjiO0mIIbY40MlrtSUupL0tOxiTtYZAhYyew",
                                                "refreshToken": "caa84715-923d-4f60-b547-855c8ef6659a"
                                            }
                                        """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "El refresh token introducido no se encuentra registrado",
                    content = @Content)
    })
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
