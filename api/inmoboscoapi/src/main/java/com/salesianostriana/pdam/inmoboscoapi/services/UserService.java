package com.salesianostriana.pdam.inmoboscoapi.services;

import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserRequest;
import com.salesianostriana.pdam.inmoboscoapi.dto.CreateUserResponse;
import com.salesianostriana.pdam.inmoboscoapi.models.User;
import com.salesianostriana.pdam.inmoboscoapi.models.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User createUser(CreateUserRequest createUserRequest, EnumSet<UserRole> roles) {
        User user = User.builder()
                .firstname(createUserRequest.getFirstname())
                .lastname(createUserRequest.getLastname())
                .username(createUserRequest.getUsername())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .phoneNumber(createUserRequest.getPhoneNumber())
                .dni(createUserRequest.getDni())
                .avatar(createUserRequest.getAvatar())
                .email(createUserRequest.getEmail())
                .birthdate(LocalDate.parse(createUserRequest.getBirthdate()))
                .rol(roles)
                .build();
        return userRepository.save(user);


    }

    public User createUserWithWorkerRole(CreateUserRequest createUserRequest){
        return createUser(createUserRequest, EnumSet.of(UserRole.WORKER));
    }

    public User createUserWithOwnerRole(CreateUserRequest createUserRequest){
        return createUser(createUserRequest, EnumSet.of(UserRole.OWNER));
    }
    public User createUserWithUserRole(CreateUserRequest createUserRequest){
        return createUser(createUserRequest, EnumSet.of(UserRole.USER));
    }

    public List<CreateUserResponse> findAllUsers() {
        List<User> data = userRepository.findAll();

        return data.stream().map(CreateUserResponse::createUserResponseFromUser).collect(Collectors.toList());
    }

    public Optional<User> findUserById(UUID id) {
        /*TODO: ESTE METODO HAY QUE MODIFICARLO CUANDO NOS PONGAMOS CON LA GESTION DE ERRORES*/
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username) {
        /*TODO: ESTE METODO HAY QUE MODIFICARLO CUANDO NOS PONGAMOS CON LA GESTION DE ERRORES*/
        return userRepository.findFirstByUsername(username);
    }

    public CreateUserResponse editUserFindByUsername(String username) {
        /*TODO: ESTE METODO HAY QUE MODIFICARLO CUANDO NOS PONGAMOS CON LA GESTION DE ERRORES*/

        /*TODO:PRGUNTAR SI AQUI MERECE LA PENA LA ENTIDAD AL COMPLETO EN VEZ DEL DTOs*/
        return userRepository.findFirstByUsername(username).map(CreateUserResponse::createUserResponseFromUser).orElseThrow();
    }

    public Optional<User> editPassword(UUID userId, String newPassword) {
        return userRepository.findById(userId).map(u -> {
            u.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(u);
        }).or(Optional::empty);

    }

    public void deleteUserByID(UUID id) {
        /*TODO: ESTE METODO HAY QUE MODIFICARLO CUANDO NOS PONGAMOS CON LA GESTION DE ERRORES*/
        if (userRepository.existsById(id)) userRepository.deleteById(id);
    }

}



