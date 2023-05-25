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
import com.salesianostriana.pdam.inmoboscoapi.user.dto.EditUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;

    @GetMapping("/user")
    public ResponseEntity<List<CreateUserResponse>> getAllUsers() {
        List<CreateUserResponse> data = userService.findAllUsers();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/profile")
    public CreateUserResponse getUserInfo(@AuthenticationPrincipal User user) {
        User u = userService.findUserByUsername(user.getUsername()).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado al usuario"));
        return CreateUserResponse.createUserResponseFromUser(u);
    }

    @PutMapping("profile")
    public CreateUserResponse editUserInfo(@RequestBody EditUserRequest newInfo, @AuthenticationPrincipal User user) {

        return CreateUserResponse.createUserResponseFromUser(EditUserRequest
                .createUserFromEditUserRequest(newInfo, user));

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

        refreshTokenService.deleteByUser(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

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


    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refreshTokenService.deleteByUser(user);
        return ResponseEntity.noContent().build();
    }

}
