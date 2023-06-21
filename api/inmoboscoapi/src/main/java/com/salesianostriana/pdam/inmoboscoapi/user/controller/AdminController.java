package com.salesianostriana.pdam.inmoboscoapi.user.controller;
import com.salesianostriana.pdam.inmoboscoapi.property.dto.PropertyResponse;
import com.salesianostriana.pdam.inmoboscoapi.property.service.PropertyService;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteria;
import com.salesianostriana.pdam.inmoboscoapi.search.util.SearchCriteriaExtractor;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.AllUserDataDto;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserFromAdminDTO;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.EditUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Worker", description = "Controlador para todas las funcionalidades de un Worker(admin)")
public class AdminController {

    private final UserService userService;
    private final PropertyService propertyService;

    @Operation(summary = "Obtén la lista paginada de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han obtenido la lista paginada de los usuarios correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllUserDataDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": "2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                                        "rol": "OWNER,USER",
                                                        "firstname": "David",
                                                        "lastname": "Garcia Maria",
                                                        "username": "Martinex",
                                                        "password": "{bcrypt}$2a$12$pMq9c8E3b0Kr4nKXuy0HM.ZzXeD9caGj4myXXPKAzXOTjDY7/8O2y",
                                                        "dni": "29551149J",
                                                        "avatar": "default.jpg",
                                                        "phoneNumber": "644617309",
                                                        "email": "vivaelsevilla@gmail.com",
                                                        "birthdate": "2002-04-26",
                                                        "createdAt": "2022-09-03T17:35:43",
                                                        "accountNonLocked": true,
                                                        "enabled": true
                                                    },
                                                    {
                                                        "id": "ac169001-999c-1ade-8188-8ccd60d80000",
                                                        "rol": "WORKER",
                                                        "firstname": "Antonio",
                                                        "lastname": "Martinez Ares",
                                                        "username": "ElBrujo",
                                                        "password": "{bcrypt}$2a$12$ygPoLGjEVtj6t1SvQnb.puJVKj3uzUQKfG3Er.p/nEvPRsaX1pb0e",
                                                        "dni": "85993325Q",
                                                        "avatar": "default.jpg",
                                                        "phoneNumber": "674526824",
                                                        "email": "vivaelcadiz@gmail.com",
                                                        "birthdate": "1998-06-12",
                                                        "createdAt": "2021-07-02T12:35:43",
                                                        "accountNonLocked": true,
                                                        "enabled": true
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
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la lista de usuarios",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Page<AllUserDataDto>> getAllUsersInfo(@RequestParam(value = "search", defaultValue = "") String search,
                                                                @PageableDefault(size = 5, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        return ResponseEntity.ok(userService.getAllUsers(params, pageable));
    }

    @Operation(summary = "Banea el acceso de un usuario buscado por su id")
    @Parameter(description = "El id del usuario que se quiere banear", name = "id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha baneado el acceso del usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AllUserDataDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "ac169001-88b6-108e-8188-b616c83f0001",
                                                "rol": "WORKER",
                                                "firstname": "Sara",
                                                "lastname": "Maria Braña",
                                                "username": "Saramb",
                                                "password": "{bcrypt}$2a$10$VOpOYwGmNpvNGnkAi/P5IueH3Um7HC0flsnXQN93fSp2qqXr9ofaK",
                                                "dni": "52266233R",
                                                "avatar": "default.jpeg",
                                                "phoneNumber": "682625268",
                                                "email": "vivaeljaen@gmail.com",
                                                "birthdate": "2005-01-11",
                                                "createdAt": "2023-06-13T20:48:46.399999",
                                                "accountNonLocked": true,
                                                "enabled": false
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún user",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content),
    })
    @PutMapping("/ban/{id}")
    public ResponseEntity<AllUserDataDto> changeUserStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(AllUserDataDto.fromUser(userService.changeUserStatus(id)));
    }

    @Operation(summary = "Como Worker(admin) borras un usuario buscado por id")
    @Parameter(description = "El id del usuario que se quiere banear", name = "id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente el usuario",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes autorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol",
                    content = @Content),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUserByID(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita un usuario buscado por id")
    @Parameter(description = "El id del usuario que se quiere banear", name = "id", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditUserRequest.class),
                    examples = {@ExampleObject(
                            value = """
                                        {
                                            "firstname":"Diego",
                                            "lastname":"Armando Maradona",
                                            "password":"lamanodedios",
                                            "username":"ElBrujo",
                                            "dni":"85226715Q",
                                            "phoneNumber":"680254908",
                                            "avatar":"https://robohash.org/81.36.52.280.png",
                                            "email":"vivamaradona@gmail.com",
                                            "birthdate":"1900-10-30"\s
                                        }
                                    """
                    )})}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha modificado correctamente el nuevo usuario con rol de User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllUserDataDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "2bd9e760-a11e-5d8f-9641-1c54e79c57a1",
                                                "rol": "OWNER,USER",
                                                "firstname": "Diego",
                                                "lastname": "Armando Maradona",
                                                "username": "ElBrujo",
                                                "password": "{bcrypt}$2a$12$pMq9c8E3b0Kr4nKXuy0HM.ZzXeD9caGj4myXXPKAzXOTjDY7/8O2y",
                                                "dni": "85226715Q",
                                                "avatar": "default.jpg",
                                                "phoneNumber": "680254908",
                                                "email": "vivamaradona@gmail.com",
                                                "birthdate": "1900-10-30",
                                                "createdAt": "2022-09-03T17:35:43",
                                                "accountNonLocked": true,
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
                                                        "message": "The phone number entered already exists",
                                                        "field": "phoneNumber",
                                                        "rejectedValue": "680254908"
                                                    },
                                                    {
                                                        "object": "editUserRequest",
                                                        "message": "This username alredy exists",
                                                        "field": "username",
                                                        "rejectedValue": "ElBrujo"
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
    @PutMapping("/profile/{id}")
    public ResponseEntity<AllUserDataDto> editUserFromAdmin(@PathVariable UUID id, @Valid @RequestBody EditUserRequest editUserRequest) {
        return ResponseEntity.ok(AllUserDataDto.fromUser(userService.editUserFindById(id, editUserRequest)));
    }

     @PostMapping("/users/")
     public ResponseEntity<CreateUserResponse> createUserFromAdmin(@Valid @RequestBody CreateUserFromAdminDTO createUserFromAdminDTO){
         return ResponseEntity.ok(CreateUserResponse.createUserResponseFromUser(userService.createUserFromAdmin(createUserFromAdminDTO)));
     }


    @Operation(summary = "Busca las propiedades de un usuario especifico")
    @Parameter(description = "El id del usuario propietario de las propiedades", name = "id", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha modificado correctamente el nuevo usuario con rol de User",
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
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado al usuario",
                    content = @Content)

    })
    @GetMapping("/user/{id}/properties")
    public ResponseEntity<Page<PropertyResponse>> getAllUserProperties(@PathVariable UUID id, @PageableDefault(size = 5, page = 0) Pageable pageable) {
        return ResponseEntity.ok(propertyService.findPropertiesByUser(userService.findUserById(id).getUsername(), pageable));
    }


}
