package com.salesianostriana.pdam.inmoboscoapi.user.controller;

import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.property.service.PropertyService;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.access.JwtProvider;
import com.salesianostriana.pdam.inmoboscoapi.security.dto.JwtUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.dto.*;
import com.salesianostriana.pdam.inmoboscoapi.others.Storage.MediaTypeUrlResource;
import com.salesianostriana.pdam.inmoboscoapi.others.Storage.StorageService;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshToken;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.pdam.inmoboscoapi.security.service.FileService;
import com.salesianostriana.pdam.inmoboscoapi.security.service.RefreshTokenService;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.AllUserDataDto;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.EditUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "Controlador para todas las funcionalidades de un Usuario")
public class UserController {

    private final PropertyService propertyService;
    private final UserService userService;
    private final StorageService storageService;
    private final RefreshTokenService refreshTokenService;
    private final FileService fileService;

    @Operation(summary = "Como usuario vas a subir tu foto de perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha subido correctamente la nueva foto de perfil del usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = URI.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "name": "SAVE_20220426_115618_803604.jpg",
                                                "uri": "http://localhost:8080/file/SAVE_20220426_115618_803604.jpg",
                                                "type": "image/jpeg",
                                                "size": 0
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })

    @PostMapping("/profile/img")
    public ResponseEntity<?> loadAvatarimg(@RequestPart("file") MultipartFile file, @AuthenticationPrincipal User user) {

        User u = userService.findUserById(user.getId());

        FileResponse fileResponse = fileService.uploadFile(file);

        userService.setAvatarToUser(fileResponse.getName(), user);

        return ResponseEntity.created(URI.create(fileResponse.getUri())).body(fileResponse);

    }

    @Operation(summary = "Como usuario vas a traer tu imagen de perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se muestra la foto de perfil del usuario",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @GetMapping("/profile/img")
    public ResponseEntity<Resource> getUserImg(@AuthenticationPrincipal User user) {
        User user1 = userService.findUserByUsername(user.getUsername());

        MediaTypeUrlResource resource =
                (MediaTypeUrlResource) storageService.loadAsResource(user1.getAvatar());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", resource.getType())
                .body(resource);
    }

    @Operation(summary = "Como usuario cierras sesión")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se muestra cierra sesión correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content)
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal User user) {
        refreshTokenService.deleteByUser(user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Como usuario me traigo mis detalles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se trae los detalles del usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "firstname": "David",
                                                "lastname": "Garcia Maria",
                                                "username": "Martinex",
                                                "avatar": "SAVE_20220426_115618_922058.jpg",
                                                "email": "vivaelsevilla@gmail.com",
                                                "rol": "OWNER,USER",
                                                "id": "2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                                "enabled": true
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @GetMapping("/profile")
    public CreateUserResponse getUserInfo(@AuthenticationPrincipal User user) {
        return CreateUserResponse.createUserResponseFromUser(userService.findUserByUsername(user.getUsername()));
    }

    @Operation(summary = "El usuario loggeado edita sus datos")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditUserRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                    {
                                             "firstname": "David",
                                             "lastname": "Garcia Mara",
                                             "dni":"69696965X",
                                             "username": "Martine",
                                             "avatar": "SAVE_20220426_115618_922058.jpg",
                                             "phoneNumber":"966958572",
                                             "email": "vivaelsevilla@gmail.com",
                                             "rol": "OWNER,USER",
                                             "id": "2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                             "birthdate":"2019-06-12"
                                         }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha modificado correctamente el usuario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateUserResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "firstname": "David",
                                                 "lastname": "Garcia Maria",
                                                 "username": "Martine",
                                                 "avatar": "default.jpg",
                                                 "email": "vivaelsevilla@gmail.com",
                                                 "rol": "OWNER,USER",
                                                 "birthdate": "2019-06-12",
                                                 "phoneNumber": "966958572",
                                                 "id": "2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                                 "enabled": true
                                             }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "Hay un error con algun campo, comprueba la lista de suberrores para localizarlo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllUserDataDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "status": "BAD_REQUEST",
                                                "message": "Validation error. Please check the sublist.",
                                                "path": "/admin/profile/2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                                "date": "13/06/2023 22:13:12",
                                                "subErrors": [
                                                     {
                                                         "object": "editUserRequest",
                                                         "message": "Phone number cannot be empty.",
                                                         "field": "phoneNumber"
                                                     },
                                                     {
                                                         "object": "editUserRequest",
                                                         "message": "{CreateUserRequest.birthdate.notempty}",
                                                         "field": "birthdate"
                                                     },
                                                     {
                                                         "object": "editUserRequest",
                                                         "message": "{CreateUserRequest.dni.notempty}",
                                                         "field": "dni"
                                                     }
                                                 ],
                                                 "statusCode": 400
                                             }
                                                """
                            )})}),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado al usuario",
                    content = @Content),
    })
    @PutMapping("/profile")
    public ResponseEntity<CreateUserResponse> editUserInfo(@Valid @RequestBody EditUserRequest newInfo, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(CreateUserResponse.createUserResponseFromUser(userService.editUser(newInfo, user)));
    }

    @Operation(summary = "Busca las propiedades del usuario loggeado")
    @Parameter(description = "Rescata el usuario del contexto de seguridad", name = "id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se trae la lista completa de mis propiedades",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllUserDataDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "content": [
                                                     {
                                                         "id": 1,
                                                         "lat": "40.4167754",
                                                         "lon": "-3.7037902",
                                                         "name": "Apartamento en el centro",
                                                         "title": "Hermoso apartamento cerca de todos los servicios",
                                                         "price": 1500.89,
                                                         "m2": 80.0,
                                                         "description": "Este apartamento luminoso y espacioso se encuentra en el corazon de la ciudad.",
                                                         "totalBedRooms": 2,
                                                         "totalBaths": 1,
                                                         "propertyType": "Apartamento",
                                                         "city": "A Coruna",
                                                         "owner": "Martinex"
                                                     },
                                                     {
                                                         "id": 2,
                                                         "lat": "41.3850639",
                                                         "lon": "2.1734035",
                                                         "name": "Casa de lujo en la playa",
                                                         "title": "Espectacular casa con vistas al mar",
                                                         "price": 2500.0,
                                                         "m2": 150.0,
                                                         "description": "Esta impresionante casa ofrece vistas panoramicas al mar y todas las comodidades necesarias.",
                                                         "totalBedRooms": 4,
                                                         "totalBaths": 3,
                                                         "propertyType": "Casa",
                                                         "city": "Granada",
                                                         "owner": "Martinex"
                                                     },
                                                     {
                                                         "id": 3,
                                                         "lat": "39.4699019",
                                                         "lon": "-0.3773957",
                                                         "name": "Piso moderno en el barrio historico",
                                                         "title": "Acogedor piso cerca de lugares historicos",
                                                         "price": 1200.0,
                                                         "m2": 100.0,
                                                         "description": "Este piso moderno y elegante se encuentra en el encantador barrio historico de la ciudad.",
                                                         "totalBedRooms": 3,
                                                         "totalBaths": 2,
                                                         "propertyType": "Piso",
                                                         "city": "Albacete",
                                                         "owner": "Martinex"
                                                     }
                                                 ],
                                                "totalElements": 2,
                                                "totalPages": 1,
                                                "size": 5,
                                                "number": 0,
                                             }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No está autorizado para realizar esta funcion",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),

    })
    @GetMapping("/me/properties/")
    public ResponseEntity<Page<PropertyResponse>> getAllPropertiesFromUser(@AuthenticationPrincipal User user, @PageableDefault(size = 5, page = 0) Pageable pageable) {
        return ResponseEntity.ok(propertyService.findPropertiesByUser(user.getUsername(), pageable));
    }

    @PostMapping("/favourites/add/{id}/")
    public ResponseEntity<PropertyResponse> addFavouriteProperty(@AuthenticationPrincipal User user, @PathVariable Long id) {
        return ResponseEntity.ok(PropertyResponse.convertPropertyResponseFromProperty(userService.addFavouriteProperty(user, id)));
    }

    @GetMapping("/favourites/")
    public ResponseEntity<Page<PropertyResponse>> getAllFavouritesProperties(@AuthenticationPrincipal User user, @PageableDefault(size = 5, page = 0) Pageable pageable) {
        return ResponseEntity.ok(userService.getAllFavouritesProperties(user, pageable));
    }

    @DeleteMapping("/favourites/{id}/")
    public ResponseEntity<Page<PropertyResponse>> removeFavouriteProperty(@AuthenticationPrincipal User user, @PageableDefault(size = 5, page = 0) Pageable pageable,
                                                                          @PathVariable Long id) {
        userService.deleteFavouriteProperty(user,id);
        return ResponseEntity.ok(userService.getAllFavouritesProperties(user, pageable));
    }
}
