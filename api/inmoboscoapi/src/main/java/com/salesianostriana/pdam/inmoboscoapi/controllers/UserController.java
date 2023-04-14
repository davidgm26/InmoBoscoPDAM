package com.salesianostriana.pdam.inmoboscoapi.controllers;

import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.salesianostriana.pdam.inmoboscoapi.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<CreateUserResponse> createUserwithUserRole(@RequestBody CreateUserRequest createUserRequest) {
        User u = userService.createUserWithUserRole(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateUserResponse.createUserResponseFromUser(u));
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


}
