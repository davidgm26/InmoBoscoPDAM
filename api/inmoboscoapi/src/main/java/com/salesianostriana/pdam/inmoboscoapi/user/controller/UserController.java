package com.salesianostriana.pdam.inmoboscoapi.user.controller;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.access.JwtProvider;
import com.salesianostriana.pdam.inmoboscoapi.security.dto.JwtUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.dto.*;
import com.salesianostriana.pdam.inmoboscoapi.others.MediaTypeUrlResource;
import com.salesianostriana.pdam.inmoboscoapi.others.StorageService;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshToken;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.pdam.inmoboscoapi.security.service.FileService;
import com.salesianostriana.pdam.inmoboscoapi.security.service.RefreshTokenService;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.user.dto.EditUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.user.User;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final StorageService storageService;

    private final RefreshTokenService refreshTokenService;
    private final FileService fileService;

    @PostMapping("/profile/img")
    public ResponseEntity<?> loadAvatarimg(@RequestPart("file") MultipartFile file, @AuthenticationPrincipal User user) {

        User u = userService.findUserById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        FileResponse fileResponse = fileService.uploadFile(file);

        userService.setAvatarToUser(fileResponse.getName(),user);

        return ResponseEntity.created(URI.create(fileResponse.getUri())).body(fileResponse);

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

    @GetMapping("/profile/img")
    public ResponseEntity<Resource> getUserImg(@AuthenticationPrincipal User user){
        User user1 = userService.findUserByUsername(user.getUsername()).orElseThrow(()-> new EntityNotFoundException("User not found"));

        MediaTypeUrlResource resource =
                (MediaTypeUrlResource) storageService.loadAsResource(user1.getAvatar());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", resource.getType())
                .body(resource);
    }

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
