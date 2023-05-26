package com.salesianostriana.pdam.inmoboscoapi.user.controller;

import com.salesianostriana.pdam.inmoboscoapi.security.jwt.access.JwtProvider;
import com.salesianostriana.pdam.inmoboscoapi.security.service.RefreshTokenService;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.EditUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refreshTokenService.deleteByUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CreateUserResponse>> getAllUsers() {
        List<CreateUserResponse> data = userService.findAllUsers();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/profile")
    public CreateUserResponse getUserInfo(@AuthenticationPrincipal User user) {
        User u = userService.findUserByUsername(user.getUsername()).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado al usuario"));
        return CreateUserResponse.createUserResponseFromUser(u);
    }

    @PutMapping("/profile")
    public CreateUserResponse editUserInfo(@RequestBody EditUserRequest newInfo, @AuthenticationPrincipal User user) {

        return CreateUserResponse.createUserResponseFromUser(EditUserRequest
                .createUserFromEditUserRequest(newInfo, user));

    }
}
